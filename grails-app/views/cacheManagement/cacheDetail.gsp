<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title><g:message code="cacheManagement.manage.title"/></title>
</head>
<body>
<div>
  <h2><g:message code="cacheManagement.manage.heading"/></h2>
  <div>
    <g:link class="button-link" action="manage"><g:message code="cacheManagement.home.link"/></g:link>    
    <g:link class="button-link" action="clearCacheStatistics" params="[cacheName:cacheName]"><g:message code="cacheManagement.clearCacheStatistics.link"/></g:link>
    <g:link class="button-link" action="clearCacheContents" params="[cacheName:cacheName]"><g:message code="cacheManagement.clearCacheContents.link"/></g:link>
    <g:link class="button-link" action="cacheContents" params="[cacheName:cacheName]"><g:message code="cacheManagement.getCacheContents.link"/></g:link>
  </div>
  <div>
    <g:render template="cacheSummary" model="[cacheSummary:cacheDetailMap]"/>
  </div>
</div>
</body>
</html>
