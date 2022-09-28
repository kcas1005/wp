function changeCategory(){
	var state = $("#selectCategory").val();
	
	if(state == "article1"){
		state = 1;
	}
	else if(state == "article2"){
		state = 2;
	}
	else if(state == "article3"){
		state = 3;
	}
	else if(state == "article4"){
		state = 4;
	}
	
	location.href="manage_article.jsp?category="+state;
	
}

function hideArticle(no,category){//다시 돌아왔을 때 현재 창으로 돌아오도록 category랑 page index값도 가지고 가야함
	location.href="hideArticle.jsp?no="+no+"&category="+category;
}

function showArticle(no,category){//다시 돌아왔을 때 현재 창으로 돌아오도록 category랑 page index값도 가지고 가야함
	location.href="showArticle.jsp?no="+no+"&category="+category;
}

function delArticle(no,category){//다시 돌아왔을 때 현재 창으로 돌아오도록 category랑 page index값도 가지고 가야함
	alert("category : "+category);
	alert("article_no : "+no);
	location.href="delArticlepro.jsp?article_no="+no+"&category="+category;
}
