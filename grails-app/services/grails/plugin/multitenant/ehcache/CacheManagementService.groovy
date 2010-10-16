package grails.plugin.multitenant.ehcache

import net.sf.ehcache.CacheManager
/**
 *
 * This service will provide support managing the cache within the system.
 */
class CacheManagementService
{
  boolean transactional = true
  /**
   * This will return a list of cache names that are currently being managed.
   * @param inParams - A map of criteria to control things like paging etc.
   * @return A List of maps containing statistics for each cache for this cache manager.
   */
  List getAllCacheDetails(Map inParams)
  {
    def cacheManager = CacheManager.getInstance()
    def cacheDetails = []
    def cacheNames = cacheManager.getCacheNames()
    cacheNames.each {name ->
      cacheDetails.add(getCacheDetails(name))
    }
    return cacheDetails.sort { it.name }
  }
  /**
   * This will return a map of cache details for the named cache.
   * @param inName - The cache to get details for
   * @return A Map containing statistics for the named cache.
   */
  Map getCacheDetails(String inName)
  {
    def cacheManager = CacheManager.getInstance()
    def details = [:]
    def cache = cacheManager.getCache(inName)
    details.name = inName
    def statistics = cache.getLiveCacheStatistics()
    details.memorySize = cache.calculateInMemorySize()
    details.averageGetTime = statistics.getAverageGetTimeMillis()
    details.cacheHits = statistics.getCacheHitCount()
    details.cacheMisses = statistics.getCacheMissCount()
    details.expireMisses = statistics.getCacheMissCountExpired()
    details.evictedCount = statistics.getEvictedCount()
    details.expiredCount = statistics.getExpiredCount()
    details.memoryHits = statistics.getInMemoryHitCount()
    details.memoryElements = statistics.getInMemorySize()
    // TODO find these missing methods
    // details.maxTimeMillis = statistics.getMaxGetTimeMillis()
    //details.minTimeMillis = statistics.getMinGetTimeMillis()
    details.diskHits = statistics.getOnDiskHitCount()
    details.diskElements = statistics.getOnDiskSize()
    details.putCount = statistics.getPutCount()
    details.removeCount = statistics.getRemovedCount()
    details.totalElements = statistics.getSize()
    details.updateCount = statistics.getUpdateCount()
    def totalRequests = details.cacheHits + details.cacheMisses
    if (totalRequests > 0)
    {
      details.hitPercentage = (details.cacheHits / totalRequests) * 100
      details.missPercentage = (details.cacheMisses / totalRequests) * 100
    }
    return details
  }
  /**
   * This will return a map of general cache statistics for the current cache
   * manager.
   * @return A map of cache statistics
   */
  Map getCacheStatistics()
  {
    def summaryStatistics = [:]
    summaryStatistics.averageGetTime = 0
    summaryStatistics.cacheHits = 0
    summaryStatistics.cacheMisses = 0
    summaryStatistics.expireMisses = 0
    summaryStatistics.evictedCount = 0
    summaryStatistics.expiredCount = 0
    summaryStatistics.memoryHits = 0
    summaryStatistics.memoryElements = 0
    summaryStatistics.maxTimeMillis = 0
    summaryStatistics.minTimeMillis = 0
    summaryStatistics.diskHits = 0
    summaryStatistics.diskElements = 0
    summaryStatistics.putCount = 0
    summaryStatistics.removeCount = 0
    summaryStatistics.totalElements = 0
    summaryStatistics.updateCount = 0
    summaryStatistics.missPercentage = 0
    summaryStatistics.hitPercentage = 0
    summaryStatistics.memorySize = 0
    def cacheManager = CacheManager.getInstance()
    // Loop over all cache names and add up statistics
    def cacheNames = cacheManager.getCacheNames()
    def count = 0
    cacheNames.each {name ->
      count++
      def cache = cacheManager.getCache(name)
      def statistics = cache.getLiveCacheStatistics()
      summaryStatistics.averageGetTime = summaryStatistics.averageGetTime + statistics.getAverageGetTimeMillis()
      summaryStatistics.cacheHits = summaryStatistics.cacheHits + statistics.getCacheHitCount()
      summaryStatistics.cacheMisses = summaryStatistics.cacheMisses + statistics.getCacheMissCount()
      summaryStatistics.expireMisses = summaryStatistics.expireMisses + statistics.getCacheMissCountExpired()
      summaryStatistics.evictedCount = summaryStatistics.evictedCount + statistics.getEvictedCount()
      summaryStatistics.expiredCount = summaryStatistics.expiredCount + statistics.getExpiredCount()
      summaryStatistics.memoryHits = summaryStatistics.memoryHits + statistics.getInMemoryHitCount()
      summaryStatistics.memoryElements = summaryStatistics.memoryElements + statistics.getInMemorySize()
      summaryStatistics.memorySize = summaryStatistics.memorySize + cache.calculateInMemorySize()
      // TODO find these missing methods
      //      if (statistics.getMaxGetTimeMillis() > summaryStatistics.maxTimeMillis){
      //          summaryStatistics.maxTimeMillis = statistics.getMaxGetTimeMillis()
      //      }
      //       if(statistics.getMinGetTimeMillis() <  summaryStatistics.minTimeMillis ||  summaryStatistics.minTimeMillis == 0)
      //       {
      //        summaryStatistics.minTimeMillis = statistics.getMinGetTimeMillis()
      //    }
      summaryStatistics.diskHits = summaryStatistics.diskHits + statistics.getOnDiskHitCount()
      summaryStatistics.diskElements = summaryStatistics.diskElements + statistics.getOnDiskSize()
      summaryStatistics.putCount = summaryStatistics.putCount + statistics.getPutCount()
      summaryStatistics.removeCount = summaryStatistics.removeCount + statistics.getRemovedCount()
      summaryStatistics.totalElements = summaryStatistics.totalElements + statistics.getSize()
      summaryStatistics.updateCount = summaryStatistics.updateCount + statistics.getUpdateCount()
    }
    if (count != 0)
    {
      summaryStatistics.averageGetTime = summaryStatistics.averageGetTime / count
    }
    def totalRequests = summaryStatistics.cacheHits + summaryStatistics.cacheMisses
    if (totalRequests > 0)
    {
      summaryStatistics.hitPercentage = (summaryStatistics.cacheHits / totalRequests) * 100
      summaryStatistics.missPercentage = (summaryStatistics.cacheMisses / totalRequests) * 100
    }
    return summaryStatistics
  }
  /**
   * This will clear out all statistics for the current cache manager.
   */
  void clearAllStatistics()
  {
    def cacheManager = CacheManager.getInstance()
    def cacheNames = cacheManager.getCacheNames()
    cacheNames.each {name ->
      def cache = cacheManager.getCache(name)
      cache.clearStatistics()
    }

  }
  /**
   * This will clear out statistics for a specific cache by name.
   */
  void clearCacheStatistics(String inCacheName)
  {
    def cacheManager = CacheManager.getInstance()
    def cache = cacheManager.getCache(inCacheName)
    cache.clearStatistics()

  }
  /**
   * This will clear out the data from all caches for the current cache manager.
   */
  void clearAll()
  {
    def cacheManager = CacheManager.getInstance()
    cacheManager.clearAll()
  }
  /**
   * This will clear out the data for a specific cache by name.
   */
  void clearCache(String inCacheName)
  {
    def cacheManager = CacheManager.getInstance()
    def cache = cacheManager.getCache(inCacheName)
    cache.removeAll()
  }
}
