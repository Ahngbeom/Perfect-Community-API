<html>
    <body>
        <div class="container-fluid g-2 h-auto ">
            <div class="d-flex gap-2 m-3 h-auto">
                <div class="d-flex flex-column col-3 border border-dark gap-3" id="boardList">
                    <div class="d-flex flex-wrap justify-content-between">
                        <label class="h5">
                            게시판
                        </label>
                        <div id="boardControlButtonsOnSideBar">
                        </div>
                    </div>
                    <div>
                        <button type="button" class="btn btn-sm btn-link board-title">전체 게시물</button>
                        <ul>
                        </ul>
                    </div>

                </div>
                <div class="d-flex flex-column col-9 gap-2 h-auto" id="mainContents">
                    <div id="additionalArea" class="border border-dark visually-hidden">
                    </div>
                    <div id="postForm" class="border border-info p-2 gap-2 visually-hidden">
                        <div class="d-flex justify-content-end p-0">
                            <button type="button" class="btn-close" aria-label="Close"></button>
                        </div>
                        <div class="d-flex justify-content-between" id="postDetailsAdditionalInfo">
                            <div class="d-flex col-8">
                                <div class="form-floating mb-3">
                                    <input id="postRegDate" class="form-control-sm form-control-plaintext" readonly/>
                                    <label for="postRegDate">Posted on</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input id="postUpdateDate" class="form-control-sm form-control-plaintext" readonly/>
                                    <label for="postUpdateDate">Updated on</label>
                                </div>
                            </div>
                            <div class="d-flex col-4">
                                <div class="form-floating mb-3 col-4">
                                    <input id="postViews" class="form-control-sm form-control-plaintext" readonly/>
                                    <label for="postViews">Views</label>
                                </div>
                                <button id="postRecommend" class="btn btn-outline-info col-4">👍<span></span></button>
                                <button id="postNotRecommend" class="btn btn-outline-danger col-4">👎<span></span>
                                </button>
                            </div>
                        </div>
                        <div class="form-floating mb-3">
                            <select name="boardNo" class="form-select w-50"
                                    aria-label="Default select example" disabled>
                            </select>
                            <label>Board where this post was registered</label>
                        </div>
                        <div class="d-flex justify-content-between mb-3">
                            <div class="form-floating">
                                <input id="postTitle" class="form-control-plaintext h4"/>
                                <label for="postTitle">Title</label>
                            </div>
                            <div class="form-floating col-3">
                                <select id="postTypeOnPostForm" name="postType" class="form-select" disabled>
                                    <option value="NOTICE">공지</option>
                                    <option value="NORMAL">일반</option>
                                </select>
                                <label for="postTypeOnPostForm">Post Type</label>
                            </div>
                        </div>
                        <div class="form-floating">
                            <textarea class='form-control-plaintext _min-vh-100' id="postContents">
                            </textarea>
                            <label for="postContents">Contents</label>
                        </div>
                        <div class="d-flex justify-content-between gap-1 mt-2">
                            <div class="col-6">
                                <div class="form-floating">
                                    <button id="postScrapBtn" class="btn btn-outline-info"><label for="postScrapBtn"
                                                                                                  class="small text-dark">Scrap</label>⭐
                                    </button>

                                </div>
                            </div>
                            <div>
                                <button type="button" id="postCreateBtn"
                                        class="btn btn-outline-info rounded-0 visually-hidden">게시
                                </button>
                                <button type="button" id="showPostUpdateFormBtn"
                                        class="btn btn-outline-warning rounded-0 visually-hidden">수정
                                </button>
                                <button type="button" id="postRemoveBtn"
                                        class="btn btn-outline-danger rounded-0 visually-hidden">삭제
                                </button>
                            </div>
                        </div>
                    </div>

                    <%-- Posts by board --%>
                    <div class="border border-success">
                        <div id="postsByBoard">
                            <div class="d-flex justify-content-between">
                                <div>
                                    <label class="h4" id="postsByBoardTitle">
                                    </label>
                                    <span id="postCount"></span>
                                </div>
                                <div id="boardControlButtonsOnMain">
                                </div>
                            </div>
                            <div class="d-flex justify-content-end">
                                <button type="button" class="btn btn-info rounded-0" id="showPostCreateFormBtn">게시물 작성
                                </button>
                            </div>
                            <ul id="postList">
                            </ul>
                            <nav class="d-flex justify-content-center visually-hidden" id="paginationNav">
                                <ul class="pagination">
                                    <li class="page-item">
                                        <button class="page-link disabled">Previous</button>
                                    </li>
                                    <li class="page-item">
                                        <button class="page-link">Next</button>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
<script type="module" src="${pageContext.request.contextPath}/resources/js/board/board.js"></script>
<script type="module" src="${pageContext.request.contextPath}/resources/js/board/preferences.js"></script>
<script type="module" src="${pageContext.request.contextPath}/resources/js/post/post.js"></script>
<script type="module" src="${pageContext.request.contextPath}/resources/js/post/list.js"></script>
<script type="module" src="${pageContext.request.contextPath}/resources/js/post/details.js"></script>
<script type="module" src="${pageContext.request.contextPath}/resources/js/post/scrap.js"></script>
<script type="module">
    import {getBoardList, putBoardList} from "../../resources/js/board/board.js";
    import {putPostList} from "../../resources/js/post/list.js";

    window.onload = () => {
        putBoardList(getBoardList());
        putPostList();
    };

</script>