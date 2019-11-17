<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<h1><c:out value="${task.task1}"/></h1>
<p>Creator: <c:out value="${task.creator}"/></p>
<p>Assignee: <c:out value="${task.assignee}"/></p>
<p>Priority: <c:out value="${task.priority}"/></p>

<c:if test="${ display eq true}">
<a href="/tasks/${task.id}/edit">Edit</a>
<a href="/tasks/${task.id}/remove">Delete</a>
</c:if>

<c:if test="${ show eq true}">
<a href="/tasks/${task.id}/remove">Completed</a>
</c:if>