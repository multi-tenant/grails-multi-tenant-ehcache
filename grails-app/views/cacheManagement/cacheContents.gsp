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
    <g:link class="button-link" action="getCacheContents" params="[cacheName:cacheName]"><g:message code="cacheManagement.getCacheContents.link"/></g:link>
  </div>
  <div>
    <table>
      <thead>
      <tr>
        <th><g:message code="cacheManagement.cacheContents.label"/></th>
      </tr>
      </thead>
      <tbody>
      <g:each in="${cacheContents}" status="i" var="content">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
          <td>${content?.encodeAsHTML()}</td>
        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
