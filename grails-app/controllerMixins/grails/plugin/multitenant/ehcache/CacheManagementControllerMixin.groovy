package grails.plugin.multitenant.ehcache
/**
 *
 * This controller will handle presenting details on the cache activity and allow
 * you to reset the cache, etc.
 */
class CacheManagementControllerMixin
{
  def cacheManagementService
  /**
   * This will return a list of tenant cache detail data for each tenant along with a summary of the entire system
   * activity.
   * @return view manage
   */
  def manage =
  {
    if (!params.max)
    {
      params.max = 10
    }
    def cacheSummary = cacheManagementService.getSystemSummary()
    def cacheSummaryList = cacheManagementService.getTenantCacheSummaries()
    render(view: 'manage', model: [cacheSummary: cacheSummary, cacheSummaryList: cacheSummaryList])
  }
  /**
   * This will gather up the statistics for cache information for a particular tenant.
   * @param params.tenantId
   * @return view tenantCacheInfo
   */
  def tenantCacheInfo =
  {
    def cacheSummary = cacheManagementService.getTenantCacheSummary(params.tenantId)
    def cacheDetailList = cacheManagementService.getCacheDetailMaps(params.tenantId, null, null)
    render(view: 'tenantCacheInfo', model: [cacheSummary: cacheSummary, cacheDetailList: cacheDetailList, tenantId: params.tenantId])
  }
  /**
   * This will present the detail statistics for a single cache in the cache
   * manager.
   * @param params.cacheName - The name of the cache to clear.
   * @return view detail
   */
  def cacheDetail =
  {
    def name = params.cacheName
    def cacheDetailMap = cacheManagementService.getCacheDetailMap(name)
    render(view: 'cacheDetail', model: [cacheDetailMap: cacheDetailMap, cacheName: params.cacheName])
  }
  /**
   * This will get the current contents of the named cache.
   * @param params.cacheName - The name of the cache to get contents for
   * @return The list of contents of the cache
   */
  def cacheContents =
  {
    def cacheContents = cacheManagementService.getCacheContents(params.cacheName)
    render(view: 'cacheContents', model: [cacheContents: cacheContents, cacheName: params.cacheName])

  }
  /**
   * This will clear out all caches for the current manager.
   * * @return redirect manage
   */
  def clearAllStatistics =
  {
    cacheManagementService.clearAllStatistics()
    redirect(controller: params.controller, action: "manage")
  }
  /**
   * This will clear out all caches for the current manager.
   * @return redirect manage
   */
  def clearAllContents =
  {
    cacheManagementService.clearAllCacheContents()
    redirect(controller: params.controller, action: "manage")
  }
  /**
   * This will clear out all caches for the selected tenant.
   * @param params.tenantId - The id of the tenant to clear statistics for
   * @return redirect manage
   */
  def clearTenantCacheStatistics =
  {
    cacheManagementService.clearTenantStatistics(params.tenantId)
    redirect(controller: params.controller, action: "tenantCacheInfo", params: [tenantId: params.tenantId])
  }
  /**
   * This will clear out all caches for the selected tenant.
   * @param params.tenantId - The tenant Id to clear cache data for
   * @return redirect manage
   */
  def clearTenantCacheContents =
  {
    cacheManagementService.clearTenantCacheContents(params.tenantId)
    redirect(controller: params.controller, action: "tenantCacheInfo", params: [tenantId: params.tenantId])
  }
  /**
   * This will clear the cache for a single named cache.
   * @param params.cacheName - The name of the cache to clear.
   * @return redirect manage
   */
  def clearCacheContents =
  {
    def name = params.cacheName
    cacheManagementService.clearCacheContents(name)
    redirect(controller: params.controller, action: "tenantCacheInfo", params: [tenantId: params.tenantId])
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
    redirect(controller: params.controller, action: "tenantCacheInfo", params: [tenantId: params.tenantId])
  }
}
