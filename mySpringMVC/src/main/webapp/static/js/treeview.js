/**
 * 加载权限菜单树形结构
 * @param divId 要放置的div
 * @param roleId 要回显权限菜单的角色id
 */
function initMenuTreeView(divId,roleId){
	var div = $("#"+divId);
	
	div.treeview({
		  data: getMenuData(roleId),         
		  levels: 5,
		  backColor: '#FFFFFF',
		  highlightSelected : false,//选择节点时是否高亮显示
		  onhoverColor : "#428bca",//鼠标滑过时颜色
		  multiSelect : true,//是否可以同时选择多个节点
		  showCheckbox: true,// 展示checkbox
		  hierarchicalCheck:true,//级联勾选
		  enableLinks: true,
		  onNodeChecked: function (event, node) {//节点选中事件
			  nodeChecked(div ,event, node);
          },
          onNodeUnchecked: function (event, node) {//节点取消选中事件
        	  nodeUnchecked(div ,event, node);
          },
	});
}





/**
 * 获取角色权限菜单数据
 * @param roleId 要回显菜单权限的角色id
 */
function getRoleMenuData(roleId){
	var roleMenuData = [];
	
	if(roleId != undefined && roleId !=null && roleId != ""){
		$.ajax({
			url:ctx+"/role/getRoleMenuList",
			type:"post",
			async:false,
			data:{roleId : roleId},
			success:function(data){
				if(null != data && undefined != data && [] != data){
					for(var i in data){
						roleMenuData.push(data[i].id);
					}
				}
				return roleMenuData;
			},
			error:function(){
				layer.alert("获取角色权限菜单失败");
				return null;
			}
		});
	}
	
	return roleMenuData;
	
}

/**
 * 获取菜单权限数据
 * @param roleId 要回显菜单权限的角色id
 */
function getMenuData(roleId){
	
	var content = []; //最终获取的JSON数据
	
	$.ajax({
		url:ctx+"/menu/getMenuList",
		type:"post",
		async:false,
		data:{},
		success:function(data){
			if(null != data && [] != data){
				//获取角色菜单数据
				var roleMenuData = getRoleMenuData(roleId);
				
				for(var i in data){
					content.push(addMenu(data[i],roleMenuData));
				}
			}
		},
		error:function(){
			layer.alert("获取菜单权限失败");
		}
	});
	
	return content;
}

/**
 * 添加菜单JSON数据
 * @param data 菜单数据
 * @param roleMenuData 角色已有菜单数据
 */
function addMenu(data,roleMenuData){
	//获取当前菜单数据
	var nowId = data.id;
	var nowText = data.name;
	var nowIcon = data.icon;
	
	//构建当前菜单
	var nowNode = {};
	nowNode.id = nowId;
	nowNode.text = nowText;
	nowNode.icon = nowIcon;
	if(null != roleMenuData && undefined != roleMenuData && [] != roleMenuData){
		if(roleMenuData.indexOf(nowId) > -1){
			var state = {checked: true, disabled: false, expanded: true, selected: true};
			nowNode.state= state;	
		}
	}
	
	//获取当前菜单的子级菜单数据	
	var childData = data.childMenuList;
	
	if(null != childData && undefined != childData && [] != childData){
		nowNode.nodes = [];
		
		for(var j in childData){
			nowNode.nodes.push(addMenu(childData[j],roleMenuData));
		}
	}
	
	return nowNode;
	
}

/**
 * 获取选中的节点
 * @param divId
 * @returns 节点数组
 */
function getSelectedNode(divId){
	var div = $("#"+divId);
	var ids = div.treeview("getChecked");
	
	return ids;
}

/**********************************自定义treeview触发函数****************************************/

var nodeCheckedSilent = false;
/**
 * 选中节点事件
 * @param div
 * @param event
 * @param node
 */
function nodeChecked (div ,event, node){
    if(nodeCheckedSilent){
        return;
    }
    nodeCheckedSilent = true;
    checkAllParent(div,node);
    checkAllSon(div,node);
    nodeCheckedSilent = false;
}
 
var nodeUncheckedSilent = false;
/**
 * 取消选中节点事件
 * @param div
 * @param event
 * @param node
 */
function nodeUnchecked  (div, event, node){
    if(nodeUncheckedSilent){
    	return;
    }
    nodeUncheckedSilent = true;
    uncheckAllParent(div,node);
    uncheckAllSon(div,node);
    nodeUncheckedSilent = false;
}
 
/**
 * 选中全部父节点
 * @param div
 * @param node
 */
function checkAllParent(div,node){
	div.treeview('checkNode',node.nodeId,{silent:true});
    var parentNode = div.treeview('getParent',node.nodeId);
    if(!("nodeId" in parentNode)){
        return;
    }else{
        checkAllParent(div,parentNode);
    }
}

/**
 * 取消选中全部父节点
 * @param div
 * @param node
 */
function uncheckAllParent(div,node){
	div.treeview('uncheckNode',node.nodeId,{silent:true});
    var siblings = div.treeview('getSiblings', node.nodeId);
    var parentNode = div.treeview('getParent',node.nodeId);
    if(!("nodeId" in parentNode)) {
        return;
    }
    var isAllUnchecked = true;  //是否全部没选中
    for(var i in siblings){
        if(siblings[i].state.checked){
            isAllUnchecked=false;
            break;
        }
    }
    if(isAllUnchecked){
        uncheckAllParent(div,parentNode);
    }
 
}
 
/**
 * 级联选中所有子节点
 * @param div
 * @param node
 */
function checkAllSon(div,node){
	div.treeview('checkNode',node.nodeId,{silent:true});
    if(node.nodes!=null && node.nodes.length>0){
        for(var i in node.nodes){
            checkAllSon(div,node.nodes[i]);
        }
    }
}

/**
 * 级联取消选中所有子节点
 * @param div
 * @param node
 */
function uncheckAllSon(div,node){
	div.treeview('uncheckNode',node.nodeId,{silent:true});
    if(node.nodes!=null && node.nodes.length>0){
        for(var i in node.nodes){
            uncheckAllSon(div,node.nodes[i]);
        }
    }
}