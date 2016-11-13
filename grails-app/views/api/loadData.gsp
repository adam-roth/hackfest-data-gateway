<g:if test="${ callback }">${ callback }({"status": "${ status }", "message":"${ message }"<g:if test="${ result }">, "result":${ result }</g:if> });</g:if>
<g:else>{"status": "${ status }", "message":"${ message }"<g:if test="${ result }">, "result":${ result }</g:if>  }</g:else>
