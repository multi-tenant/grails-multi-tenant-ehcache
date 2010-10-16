<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title><g:message code="cacheManagement.cacheDetail.heading"/></title>
</head>
<body>
<div>
  <h2><g:message code="cacheManagement.cacheDetail.heading"/></h2>
  <tcaf:flasher/>
  <div>
    <g:link class="button-link" action="clearCacheStatistics" params="[cacheName:cacheDetail?.name]"><g:message code="cacheManagement.clearCacheStatistics.link"/></g:link>
    <g:link class="button-link" action="clearCache" params="[cacheName:cacheDetail?.name]"><g:message code="cacheManagement.clearCacheContents.link"/></g:link>
    <g:link class="button-link" action="manage"><g:message code="cancel.link"/></g:link>
  </div>
  <h3>${cacheDetail?.name?.encodeAsHTML()}</h3>
  <ui:panel code="cacheManagement.statistics.panel.label">
    <table class="admin">
      <tr>
        <td><g:message code="cacheManagement.cacheHits.label"/></td>
        <td>${cacheDetail?.cacheHits}</td>
        <td><g:message code="cacheManagement.cacheMisses.label"/></td>
        <td>${cacheDetail?.cacheMisses}</td>
      </tr>
      <tr>
        <td><g:message code="cacheManagement.evictionCount.label"/></td>
        <td>${cacheDetail?.evictedCount}</td>
        <td><g:message code="cacheManagement.expireCount.label"/></td>
        <td>${cacheDetail?.expiredCount}</td>
      </tr>
      <tr>
        <td><g:message code="cacheManagement.memoryHits.label"/></td>
        <td>${cacheDetail?.memoryHits}</td>
        <td><g:message code="cacheManagement.memoryElements.label"/></td>
        <td>${cacheDetail?.memoryElements}</td>
      </tr>
      <tr>
        <td><g:message code="cacheManagement.averageGetTime.label"/></td>
        <td><g:formatNumber number="${cacheDetail?.averageGetTime * 1000}"/></td>
        <td><g:message code="cacheManagement.minAccessTime.label"/></td>
        <td>${cacheDetail?.minAccessTime}xx</td>
      </tr>
      <tr>
        <td><g:message code="cacheManagement.maxAccessTime.label"/></td>
        <td>${cacheDetail?.maxAccessTime}xx</td>
        <td><g:message code="cacheManagement.diskHits.label"/></td>
        <td>${cacheDetail?.diskHits}</td>
      </tr>
      <tr>
        <td><g:message code="cacheManagement.diskElements.label"/></td>
        <td>${cacheDetail?.diskElements}</td>
        <td><g:message code="cacheManagement.putCount.label"/></td>
        <td>${cacheDetail?.putCount}</td>
      </tr>
      <tr>
        <td><g:message code="cacheManagement.removeCount.label"/></td>
        <td>${cacheDetail?.removeCount}</td>
        <td><g:message code="cacheManagement.totalElements.label"/></td>
        <td>${cacheDetail?.totalElements}</td>
      </tr>
      <tr>
        <td><g:message code="cacheManagement.updateCount.label"/></td>
        <td>${cacheDetail?.updateCount}</td>
        <td><g:message code="cacheManagement.hitPercent.label"/></td>
        <td><g:formatNumber number="${cacheDetail?.hitPercentage}"/>%</td>
      </tr>
      <tr>
        <td><g:message code="cacheManagement.missPercent.label"/></td>
        <td><g:formatNumber number="${cacheDetail?.missPercentage}"/>%</td>
        <td><g:message code="cacheManagement.memorySize.label"/></td>
        <td><g:formatNumber number="${cacheDetail?.memorySize}" format="###,###"/></td>
      </tr>
    </table>
  </ui:panel>
</div>
</body>
</html>
