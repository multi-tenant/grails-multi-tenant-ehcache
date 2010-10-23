package grails.plugin.multitenant.ehcache

import net.sf.ehcache.CacheManager
/**
 *
 * This service will provide support managing the eh-cache within a multi-tenant single database per
 * client implementation.  It provides the ability to drill down into details for each tenant and each cache within
 * the tenant.  It also provides an implementation of the cache that will support configuration of cache components
 * on a per client and per cache entry basis.
 */
class CacheManagementService
{
  boolean transactional = true
  /**
   * This will return a summary of the cache statistics for the entire system.  It will return those statistics in a map
   * as described in the createSummaryCacheMap method.  The summary data will include every cache in the system combining
   * all caches including those managed by the multi-tenant plugin as well as any external ones.
   * @return A Map of statistics for all caches in the system.
   */
  def getSystemSummary()
  {
    // Get all caches in the system
    return getCacheSummaryMap(CacheManager.getInstance().getCacheNames())
  }
  /**
   * This will return a list of tenant cache summary items.  The summary items will be stored in a list of maps with the
   * following keys:
   * tenantId - The unique id for the tenant <br/>
   * tenantName - The name of the tenant <br/>
   * cacheSummary - This is a map of summary statics for each tenant.  See the createSummaryCacheMap method for details on 
   * how this map is structured <br/>
   * @return A List of maps one for each tenant with each map containing the data describe above.
   */
  def getTenantCacheSummaries()
  {
    def summaries = []
    // TODO
    (1..2).each {
      def summaryMap = getCacheSummaryMap(getTenantCacheNames(it))
      summaryMap.tenantName = it
      summaryMap.tenantId = it
      summaries.add(summaryMap)
    }
    return summaries
    // Loop over all tenants
    // Get Summary data for each tenant
    // Get the summary data for any non tenant specific cache entries and lump them together
  }
  /**
   * This will return a tenant cache summary mapt for the tenant requested.
   * The summary items will be stored in a list of maps with the
   * following keys:
   * tenantId - The unique id for the tenant <br/>
   * tenantName - The name of the tenant <br/>
   * cacheSummary - This is a map of summary statics for each tenant.  See the createSummaryCacheMap method for details on
   * how this map is structured <br/>
   * @return A map for the tenant with containing the data describe above.
   */
  def getTenantCacheSummary(def inTenantId)
  {
    return getCacheSummaryMap(getTenantCacheNames(inTenantId))
  }
  /**
   * This will return a list of cache detail map entries for every cache used by the tenant passed in.  If the tenant id
   * is null then the non tenant entries will be returned.   See the method  getCacheDetailMap for the descriiption of
   * what keys are in the map.
   * @param inTenantId - The id to get cache detail entries for.  If the tenant Id is null then the non tenant entries
   * are returned.
   * @param inFirstRecord - The first record that is returned based on 1 as the first record.
   * @param inMaxRecords - The maximum number of records to return.
   * @return A List of maps containing cache details for each cache the tenant has created.
   */
  List getCacheDetailMaps(def inTenantId, def inFirstRecord, def inMaxRecords)
  {
    def tenantCacheEntries = getTenantCacheNames(inTenantId)
    def cacheDetails = []
    tenantCacheEntries.each {name ->
      cacheDetails.add(getCacheDetailMap(name))
    }
    return cacheDetails.sort { it.name }
  }

  /**
   * This will clear out all statistics for the current default cache manager.
   */
  void clearAllStatistics()
  {
    def cacheManager = CacheManager?.getInstance()
    def cacheNames = cacheManager?.getCacheNames()
    cacheNames?.each {name ->
      cacheManager?.getCache(name)?.clearStatistics()
    }

  }
  /**
   * This will clear out statistics for all caches for a tenant.
   * @param inTenantId - The id of the tenant to clear cache statistics for.
   */
  void clearTenantStatistics(def inTenantId)
  {
    def tenantCacheEntries = getTenantCacheNames(inTenantId)
    tenantCacheEntries.each {name ->
      clearCacheStatistics(name)
    }
  }
  /**
   * This will clear out statistics for a specific cache by name.
   * @param inCacheName - The name of the cache to clear out statistics for.
   */
  void clearCacheStatistics(String inCacheName)
  {
    CacheManager.getInstance()?.getCache(inCacheName)?.clearStatistics()
  }
  /**
   * This will clear out the DATA from all caches for the current default cache manager. This includes all caches even
   * those not managed by the multi tenant plugin.
   */
  void clearAllCacheContents()
  {
    CacheManager?.getInstance()?.clearAll()
  }
  /**
   * This will clear out data for all the caches for a tenant.
   * @param inTenantId - The id of the tenant to clear cache data for.
   */
  void clearTenantCacheContents(def inTenantId)
  {
    def tenantCacheEntries = getTenantCacheNames(inTenantId)
    tenantCacheEntries.each {name ->
      clearCacheContents(name)
    }
  }
  /**
   * This will clear out the DATA for a specific cache by name.
   * @param inCacheName - The of the cache to clear out data from.
   */
  void clearCacheContents(String inCacheName)
  {
    CacheManager?.getInstance()?.getCache(inCacheName)?.removeAll()
  }
  /**
   * This will produce a map of summary cache data.  The method will take in a list of cache entry names and produce
   * a summary map for the total cache entries.  The entries in the map will be as follows:
   * averageGetTime <br/>
   * cacheHits <br/>
   * cacheMisses <br/>
   * expireMisses <br/>
   * evictedCount <br/>
   * expiredCount <br/>
   * memoryHits <br/>
   * memoryElements <br/>
   * maxTimeMillis <br/>
   * minTimeMillis <br/>
   * diskHits <br/>
   * diskElements <br/>
   * putCount <br/>
   * removeCount <br/>
   * totalElements <br/>
   * updateCount <br/>
   * missPercentage <br/>
   * hitPercentage <br/>
   * memorySize <br/>
   * @param inCacheNames - The list of cache names to produce statistics for.
   * @return A map of summary cache data for the list of cache names passed in.
   */
  def getCacheSummaryMap(def inCacheNames)
  {
    def summaryMap = [:]
    summaryMap.averageGetTime = 0
    summaryMap.cacheHits = 0
    summaryMap.cacheMisses = 0
    summaryMap.expireMisses = 0
    summaryMap.evictedCount = 0
    summaryMap.expiredCount = 0
    summaryMap.memoryHits = 0
    summaryMap.memoryElements = 0
    summaryMap.maxTimeMillis = 0
    summaryMap.minTimeMillis = 0
    summaryMap.diskHits = 0
    summaryMap.diskElements = 0
    summaryMap.putCount = 0
    summaryMap.removeCount = 0
    summaryMap.totalElements = 0
    summaryMap.updateCount = 0
    summaryMap.missPercentage = 0
    summaryMap.hitPercentage = 0
    summaryMap.memorySize = 0
    def cacheManager = CacheManager?.getInstance()
    def count = 0
    inCacheNames?.each {name ->
      count++
      def cache = cacheManager?.getCache(name)
      def statistics = cache?.getLiveCacheStatistics()
      summaryMap.averageGetTime = summaryMap.averageGetTime + statistics?.getAverageGetTimeMillis()
      summaryMap.cacheHits = summaryMap.cacheHits + statistics?.getCacheHitCount()
      summaryMap.cacheMisses = summaryMap.cacheMisses + statistics?.getCacheMissCount()
      summaryMap.expireMisses = summaryMap.expireMisses + statistics?.getCacheMissCountExpired()
      summaryMap.evictedCount = summaryMap.evictedCount + statistics?.getEvictedCount()
      summaryMap.expiredCount = summaryMap.expiredCount + statistics?.getExpiredCount()
      summaryMap.memoryHits = summaryMap.memoryHits + statistics?.getInMemoryHitCount()
      summaryMap.memoryElements = summaryMap.memoryElements + statistics?.getInMemorySize()
      summaryMap.memorySize = summaryMap.memorySize + cache?.calculateInMemorySize()
      // TODO find these missing methods
      //      if (statistics.getMaxGetTimeMillis() > summaryMap.maxTimeMillis){
      //          summaryMap.maxTimeMillis = statistics?.getMaxGetTimeMillis()
      //      }
      //       if(statistics.getMinGetTimeMillis() <  summaryMap.minTimeMillis ||  summaryMap.minTimeMillis == 0)
      //       {
      //        summaryMap.minTimeMillis = statistics?.getMinGetTimeMillis()
      //    }
      summaryMap.diskHits = summaryMap.diskHits + statistics?.getOnDiskHitCount()
      summaryMap.diskElements = summaryMap.diskElements + statistics?.getOnDiskSize()
      summaryMap.putCount = summaryMap.putCount + statistics?.getPutCount()
      summaryMap.removeCount = summaryMap.removeCount + statistics?.getRemovedCount()
      summaryMap.totalElements = summaryMap.totalElements + statistics?.getSize()
      summaryMap.updateCount = summaryMap.updateCount + statistics?.getUpdateCount()
    }
    if (count != 0)
    {
      summaryMap.averageGetTime = summaryMap.averageGetTime / count
    }
    def totalRequests = summaryMap.cacheHits + summaryMap.cacheMisses
    if (totalRequests > 0)
    {
      summaryMap.hitPercentage = (summaryMap.cacheHits / totalRequests) * 100
      summaryMap.missPercentage = (summaryMap.cacheMisses / totalRequests) * 100
    }
    return summaryMap
  }
  /**
   * This will return a map of cache details for the named cache with keys as detailed below.
   * cacheName - The name of the cache the statistics are for.<br/>
   * memorySize <br/>
   * averageGetTime <br/>
   * cacheHits <br/>
   * cacheMisses <br/>
   * expireMisses <br/>
   * evictedCount <br/>
   * expiredCount <br/>
   * memoryHits <br/>
   * memoryElements <br/>
   * maxTimeMillis <br/>
   * minTimeMillis <br/>
   * diskHits <br/>
   * diskElements <br/>
   * putCount <br/>
   * removeCount <br/>
   * totalElements <br/>
   * updateCount <br/>
   * hitPercentage <br/>
   * missPercentage <br/>
   * @param inCacheName - The name of the cache to get details for
   * @return A Map containing statistics for the named cache.
   */
  Map getCacheDetailMap(String inCacheName)
  {
    def cacheManager = CacheManager?.getInstance()
    def detailMap = [:]
    def cache = cacheManager?.getCache(inCacheName)
    detailMap.name = inCacheName
    def statistics = cache?.getLiveCacheStatistics()
    detailMap.memorySize = cache?.calculateInMemorySize()
    detailMap.averageGetTime = statistics?.getAverageGetTimeMillis()
    detailMap.cacheHits = statistics?.getCacheHitCount()
    detailMap.cacheMisses = statistics?.getCacheMissCount()
    detailMap.expireMisses = statistics?.getCacheMissCountExpired()
    detailMap.evictedCount = statistics?.getEvictedCount()
    detailMap.expiredCount = statistics?.getExpiredCount()
    detailMap.memoryHits = statistics?.getInMemoryHitCount()
    detailMap.memoryElements = statistics?.getInMemorySize()
    // TODO find these missing methods
    // detailMap.maxTimeMillis = statistics.getMaxGetTimeMillis()
    //detailMap.minTimeMillis = statistics.getMinGetTimeMillis()
    detailMap.diskHits = statistics?.getOnDiskHitCount()
    detailMap.diskElements = statistics?.getOnDiskSize()
    detailMap.putCount = statistics?.getPutCount()
    detailMap.removeCount = statistics?.getRemovedCount()
    detailMap.totalElements = statistics?.getSize()
    detailMap.updateCount = statistics?.getUpdateCount()
    def totalRequests = detailMap?.cacheHits + detailMap?.cacheMisses
    if (totalRequests > 0)
    {
      detailMap?.hitPercentage = (detailMap?.cacheHits / totalRequests) * 100
      detailMap?.missPercentage = (detailMap?.cacheMisses / totalRequests) * 100
    }
    return detailMap
  }
  /**
   * This will return a list of contents of a cache by name
   * @param inCacheName - The name of the cache to get the contents of
   * @return A list of contents for the nameed cache
   */
  def getCacheContents(def inCacheName)
  {
    def cacheMap = [:]
    def cache = CacheManager.getInstance()?.getCache(inCacheName)
    def keys = cache.getKeys()
    keys?.each {key ->
      cacheMap[key] = cache.get(key)
    }
    return cacheMap
  }
  /**
   * This will get a list of cache names for a specific tenant
   * @param inTenantId - The id of the tenant to get cache names for.
   * @return A list of cacheNames for a specific tenant
   */
  def getTenantCacheNames(def inTenantId)
  {
    // TODO Optimize this
    def returnNames = []
    CacheManager.getInstance().getCacheNames().each {name ->
      if (name.startsWith(inTenantId + "-"))
      {
        returnNames.add(name)
      }
    }
    return returnNames
  }
}
