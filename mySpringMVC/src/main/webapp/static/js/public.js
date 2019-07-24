/**
 * 关闭所有弹出的layer页面
 */
function closeAllLayer(){
	parent.layer.closeAll();
	layer.closeAll();
}

/**
 * 重置查询表头查询条件
 */
function resetSearch(){
	// 重置text
	$(".search-from [type='text']").each(function(index, element) {
		$(this).val("");
	});

	// 重置textare
	$(".search-from [type='textarea']").each(function(index, element) {
		$(this).val("");
	});

	// 重置select
	$(".search-from select").each(function(index, element) {
		$(this).find("option").first().prop("selected", true);
	});
	
}

/**
 * 上传图片预览
 * @param fileDom file元素本身
 * @param imgId 要展示图片的img元素id
 */
function previewImage(fileDom,imgId){
	//获取文件
    var file = fileDom.files[0];
    var supportedTypes = ['image/jpg', 'image/jpeg', 'image/png'];
    if(!(supportedTypes.indexOf(file.type)>-1)){
        layer.alert('请选择.jpg/.png格式图片');
        return;
    }
    
    var url = null ;
    if (window.createObjectURL!=undefined) { 
      // basic
      url = window.createObjectURL(file) ;
    }
    else if (window.URL!=undefined) {
      // mozilla(firefox)
      url = window.URL.createObjectURL(file) ;
    } else if (window.webkitURL!=undefined) {
      // webkit or chrome
      url = window.webkitURL.createObjectURL(file) ;
    }
    
    $("#"+imgId).attr("src",url);
}

