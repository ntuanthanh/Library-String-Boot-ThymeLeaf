function Pagination(id,gap,totalPage,pageIndex,url_param){
	var container = document.getElementById('paggerBottom');
	result = '';
	if(pageIndex - gap > 1){
	   result += '<li class="page-item"><a class="page-link" href="'+url_param+'page=1">First</a></li>';
    }
	// mới thêm
	if(pageIndex > 1){
		result += '<li class="page-item"><a class="page-link" href="'+url_param+'page='+(pageIndex - 1)+'">Previous</a></li>';
	}
	for(var i = pageIndex - gap; i < pageIndex; i++){
		if(i > 0)
		  result += '<li class="page-item"><a class="page-link" href="'+url_param+'page='+i+'">'+i+'</a></li>';
	}
	result += '<li class="page-item active"><span class="page-link">'+pageIndex+'</span></li>';		   
	for(var i = pageIndex + 1; i <= pageIndex + gap; i++){
		if(i <= totalPage)
		  result += '<li class="page-item"><a class="page-link" href="'+url_param+'page='+i+'">'+i+'</a></li>';
	}
	// mới thêm
	if(pageIndex < totalPage){
		result += '<li class="page-item"><a class="page-link" href="'+url_param+'page='+(pageIndex + 1)+'">Next</a></li>';
	}
	if(pageIndex + gap < totalPage){
		result += '<li class="page-item"><a class="page-link" href="'+url_param+'page='+totalPage+'">Last</a></li>';
	}
	container.innerHTML = result;
}