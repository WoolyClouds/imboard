<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="paginate">
    <a href="boardlist.go?curpage=${paging.firstPageNo }" class="first"> 처음 </a>
    <a href="boardlist.go?curpage=${paging.prevPageNo }" class="prev"> 이전 </a>
    <span>
        <c:forEach var="i" begin="${param.startPageNo}" end="${param.endPageNo}" step="1">
            <c:choose>
                <c:when test="${i eq param.pageNo}"><a href="boardlist.go?curpage=${i }" >${i}</a></c:when>
                <c:otherwise><a href="boardlist.go?curpage=${i }">${i}</a></c:otherwise>
            </c:choose>
        </c:forEach>
    </span>
    <a href="boardlist.go?curpage=${param.nextPageNo}" class="next"> 다음 </a>
    <a href="boardlist.go?curpage=${param.finalPageNo }" class="last"> 끝 </a>
</div>


