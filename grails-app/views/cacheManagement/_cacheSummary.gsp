<table class="admin">
  <tr>
    <td><g:message code="cacheManagement.cacheHits.label"/></td>
    <td>${cacheSummary?.cacheHits}</td>
    <td><g:message code="cacheManagement.cacheMisses.label"/></td>
    <td>${cacheSummary?.cacheMisses}</td>
  </tr>
  <tr>
    <td><g:message code="cacheManagement.evictionCount.label"/></td>
    <td>${cacheSummary?.evictedCount}</td>
    <td><g:message code="cacheManagement.expireCount.label"/></td>
    <td>${cacheSummary?.expiredCount}</td>
  </tr>
  <tr>
    <td><g:message code="cacheManagement.memoryHits.label"/></td>
    <td>${cacheSummary?.memoryHits}</td>
    <td><g:message code="cacheManagement.memoryElements.label"/></td>
    <td>${cacheSummary?.memoryElements}</td>
  </tr>
  <tr>
    <td><g:message code="cacheManagement.averageGetTime.label"/></td>
    <td><g:formatNumber number="${cacheSummary?.averageGetTime * 1000}"/></td>
    <td><g:message code="cacheManagement.minAccessTime.label"/></td>
    <td>${cacheSummary?.minAccessTime}xx</td>
  </tr>
  <tr>
    <td><g:message code="cacheManagement.maxAccessTime.label"/></td>
    <td>${cacheSummary?.maxAccessTime}xx</td>
    <td><g:message code="cacheManagement.diskHits.label"/></td>
    <td>${cacheSummary?.diskHits}</td>
  </tr>
  <tr>
    <td><g:message code="cacheManagement.diskElements.label"/></td>
    <td>${cacheSummary?.diskElements}</td>
    <td><g:message code="cacheManagement.putCount.label"/></td>
    <td>${cacheSummary?.putCount}</td>
  </tr>
  <tr>
    <td><g:message code="cacheManagement.removeCount.label"/></td>
    <td>${cacheSummary?.removeCount}</td>
    <td><g:message code="cacheManagement.totalElements.label"/></td>
    <td>${cacheSummary?.totalElements}</td>
  </tr>
  <tr>
    <td><g:message code="cacheManagement.updateCount.label"/></td>
    <td>${cacheSummary?.updateCount}</td>
    <td><g:message code="cacheManagement.hitPercent.label"/></td>
    <td><g:formatNumber number="${cacheSummary?.hitPercentage}"/>%</td>
  </tr>
  <tr>
    <td><g:message code="cacheManagement.missPercent.label"/></td>
    <td><g:formatNumber number="${cacheSummary?.missPercentage}"/>%</td>
    <td><g:message code="cacheManagement.memorySize.label"/></td>
    <td><g:formatNumber number="${cacheSummary?.memorySize}" format="###,###"/></td>
  </tr>
</table>