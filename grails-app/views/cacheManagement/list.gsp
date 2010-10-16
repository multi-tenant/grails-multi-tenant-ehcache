<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title><g:message code="cacheManagement.list.heading"/></title>
</head>
<body>
<div>
  <h2><g:message code="cacheManagement.list.heading"/></h2>
  <tcaf:flasher/>
  <div>
    <g:link class="button-link" action="clearAllStatistics"><g:message code="cacheManagement.clearAllStatistics.link"/></g:link>
    <g:link class="button-link" action="clearAll"><g:message code="cacheManagement.clearAllContents.link"/></g:link>
  </div>
  <ui:panel code="cacheManagement.statistics.panel.label">
    <table class="admin">
      <tr>
        <td><g:message code="cacheManagement.cacheHits.label"/></td>
        <td>${cacheStatistics?.cacheHits}</td>
        <td><g:message code="cacheManagement.cacheMisses.label"/></td>
        <td>${cacheStatistics?.cacheMisses}</td>
      </tr>
      <tr>
        <td><g:message code="cacheManagement.evictionCount.label"/></td>
        <td>${cacheStatistics?.evictedCount}</td>
        <td><g:message code="cacheManagement.expireCount.label"/></td>
        <td>${cacheStatistics?.expiredCount}</td>
      </tr>
      <tr>
        <td><g:message code="cacheManagement.memoryHits.label"/></td>
        <td>${cacheStatistics?.memoryHits}</td>
        <td><g:message code="cacheManagement.memoryElements.label"/></td>
        <td>${cacheStatistics?.memoryElements}</td>
      </tr>
      <tr>
        <td><g:message code="cacheManagement.averageGetTime.label"/></td>
        <td><g:formatNumber number="${cacheStatistics?.averageGetTime * 1000}"/></td>
        <td><g:message code="cacheManagement.minAccessTime.label"/></td>
        <td>${cacheStatistics?.minAccessTime}xx</td>
      </tr>
      <tr>
        <td><g:message code="cacheManagement.maxAccessTime.label"/></td>
        <td>${cacheStatistics?.maxAccessTime}xx</td>
        <td><g:message code="cacheManagement.diskHits.label"/></td>
        <td>${cacheStatistics?.diskHits}</td>
      </tr>
      <tr>
        <td><g:message code="cacheManagement.diskElements.label"/></td>
        <td>${cacheStatistics?.diskElements}</td>
        <td><g:message code="cacheManagement.putCount.label"/></td>
        <td>${cacheStatistics?.putCount}</td>
      </tr>
      <tr>
        <td><g:message code="cacheManagement.removeCount.label"/></td>
        <td>${cacheStatistics?.removeCount}</td>
        <td><g:message code="cacheManagement.totalElements.label"/></td>
        <td>${cacheStatistics?.totalElements}</td>
      </tr>
      <tr>
        <td><g:message code="cacheManagement.updateCount.label"/></td>
        <td>${cacheStatistics?.updateCount}</td>
        <td><g:message code="cacheManagement.hitPercent.label"/></td>
        <td><g:formatNumber number="${cacheStatistics?.hitPercentage}"/>%</td>
      </tr>
      <tr>
        <td><g:message code="cacheManagement.missPercent.label"/></td>
        <td><g:formatNumber number="${cacheStatistics?.missPercentage}"/>%</td>
        <td><g:message code="cacheManagement.memorySize.label"/></td>
        <td><g:formatNumber number="${cacheStatistics?.memorySize}" format="###,###"/></td>
      </tr>
    </table>
  </ui:panel>
  <div>
    <table id="cacheTable" class="admin">
      <thead>
      <tr>
        <th><g:message code="cacheManagement.name.label"/></th>
        <th><g:message code="cacheManagement.totalElements.label"/></th>
        <th><g:message code="cacheManagement.hitPercent.label"/></th>
        <th><g:message code="cacheManagement.missPercent.label"/></th>
        <th>&nbsp;</th>
      </tr>
      </thead>
      <tbody>
      <g:each in="${cacheDetailList}" status="i" var="detail">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
          <td>${detail?.name?.encodeAsHTML()}</td>
          <td>${detail?.totalElements?.encodeAsHTML()}</td>
          <td><g:formatNumber number="${detail?.hitPercentage?.encodeAsHTML()}"/>%</td>
          <td><g:formatNumber number="${detail?.missPercentage?.encodeAsHTML()}"/>%</td>
          <td>
            <g:link class="button-link" action="clearCacheStatistics" params="[cacheName:detail?.name]"><g:message code="cacheManagement.clearCacheStatistics.link"/></g:link>
            <g:link class="button-link" action="clearCache" params="[cacheName:detail?.name]"><g:message code="cacheManagement.clearCacheContents.link"/></g:link>
            <g:link class="button-link" action="cacheDetail" params="[cacheName:detail?.name]"><g:message code="cacheManagement.cacheDetails.link"/></g:link>
          </td>
        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
