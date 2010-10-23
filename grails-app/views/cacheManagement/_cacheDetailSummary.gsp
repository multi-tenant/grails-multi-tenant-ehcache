<div>
  <table id="cacheTable" class="admin">
    <thead>
    <tr>
      <th><g:message code="cacheManagement.cacheName.label"/></th>
      <th><g:message code="cacheManagement.totalElements.label"/></th>
      <th><g:message code="cacheManagement.hitPercent.label"/></th>
      <th><g:message code="cacheManagement.missPercent.label"/></th>
      <th><g:message code="cacheManagement.memorySize.label"/></th>
      <th>&nbsp;</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${cacheDetailList}" status="i" var="detail">
      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
        <td>${detail?.name?.encodeAsHTML()}</td>
        <td>${detail?.totalElements?.encodeAsHTML()}</td>
        <td><g:formatNumber number="${detail?.hitPercentage}"/>%</td>
        <td><g:formatNumber number="${detail?.missPercentage}"/>%</td>
        <td><g:formatNumber number="${detail?.memorySize}"/></td>
        <td>
          <g:link class="button-link" action="clearCacheStatistics" params="[cacheName:detail?.name,tenantId:tenantId]"><g:message code="cacheManagement.clearCacheStatistics.link"/></g:link>
          <g:link class="button-link" action="clearCacheContents" params="[cacheName:detail?.name,tenantId:tenantId]"><g:message code="cacheManagement.clearCacheContents.link"/></g:link>
          <g:link class="button-link" action="cacheDetail" params="[cacheName:detail?.name]"><g:message code="cacheManagement.cacheDetails.link"/></g:link>
        </td>
      </tr>
    </g:each>
    </tbody>
  </table>
</div>