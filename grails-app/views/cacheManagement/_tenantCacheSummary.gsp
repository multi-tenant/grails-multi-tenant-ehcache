<div>
  <table>
    <thead>
    <tr>
      <th><g:message code="cacheManagement.tenantName.label"/></th>
      <th><g:message code="cacheManagement.totalElements.label"/></th>
      <th><g:message code="cacheManagement.hitPercent.label"/></th>
      <th><g:message code="cacheManagement.missPercent.label"/></th>
      <th><g:message code="cacheManagement.memorySize.label"/></th>
      <th>&nbsp;</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${cacheSummaryList}" status="i" var="summary">
      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
        <td>${summary?.tenantName?.encodeAsHTML()}</td>
        <td>${summary?.totalElements?.encodeAsHTML()}</td>
        <td><g:formatNumber number="${summary?.hitPercentage}"/>%</td>
        <td><g:formatNumber number="${summary?.missPercentage}"/>%</td>
        <td><g:formatNumber number="${summary?.memorySize}"/></td>
        <td>
          <g:link class="button-link" action="clearTenantCacheStatistics" params="[tenantId:summary?.tenantId]"><g:message code="cacheManagement.clearTenantCacheStatistics.link"/></g:link>
          <g:link class="button-link" action="clearTenantCacheContents" params="[tenantId:summary?.tenantId]"><g:message code="cacheManagement.clearTenantCacheContents.link"/></g:link>
          <g:link class="button-link" action="tenantCacheInfo" params="[tenantId:summary?.tenantId]"><g:message code="cacheManagement.tenantCacheDetails.link"/></g:link>
        </td>
      </tr>
    </g:each>
    </tbody>
  </table>
</div>