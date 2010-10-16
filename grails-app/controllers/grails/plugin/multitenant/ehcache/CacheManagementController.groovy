package grails.plugin.multitenant.ehcache
/**
 *
 * This controller will handle presenting details on the cache activity and allow
 * you to reset the cache, etc.
 */
class CacheManagementController
{
  def cacheManagementService
  /**
   * This will return a list of cache names as well as a summary of cache data for
   * all caches in the system.
   */
  def manage =
  {
    // If not set to 10 then set to 10
    if (!params.max)
    {
      params.max = 10
    }
    def cacheDetailList = cacheManagementService.getAllCacheDetails(params)
    def cacheStatistics = cacheManagementService.getCacheStatistics()
    render(view: 'list', model: [cacheStatistics: cacheStatistics, cacheDetailList: cacheDetailList])
  }
  /**
   * This will present the detail statistics for a single cache in the cache
   * manager.
   * * @param params.cacheName - The name of the cache to clear.
   * @return view detail
   */
  def cacheDetail =
  {
    def name = params.cacheName
    def cacheDetail = cacheManagementService.getCacheDetails(name)
    [cacheDetail: cacheDetail]
  }
  /**
   * This will clear out all caches for the current manager.
   * * @return redirect manage
   */
  def clearAll =
  {
    cacheManagementService.clearAll()
    redirect(action: manage)
  }
  /**
   * This will clear the cache for a single named cache.
   * @param params.cacheName - The name of the cache to clear.
   * @return redirect manage
   */
  def clearCache =
  {
    def name = params.cacheName
    cacheManagementService.clearCache(name)
    redirect(action: manage)
  }
  /**
   * This will clear out all caches for the current manager.
   * * @return redirect manage
   */
  def clearAllStatistics =
  {
    cacheManagementService.clearAllStatistics()
    redirect(action: manage)
  }
  /**
   * This will clear the cache for a single named cache.
   * @param params.cacheName - The name of the cache to clear.
   * @return redirect manage
   */
  def clearCacheStatistics =
  {
    def name = params.cacheName
    cacheManagementService.clearCacheStatistics(name)
    redirect(action: manage)
  }
}
