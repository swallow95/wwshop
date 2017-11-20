
var wwshop = {
    //点击左侧导航树上的节点，添加选项卡
    //点击导航树上的动作
    registerMenuEvent:function(){
        var _this = this;
        //约定大于配置：jquery对象前面加上$,如果是DOM对象不需要加$
        var $tree = $('#menu .easyui-tree');
        $tree.tree({
            onClick:function(node){
                var href = node.attributes.href;//item-add
                var text = node.text;
                // console.log(this); ul
                // console.log(_this); wwshop
                _this.addTabs(text,href);
            }
        });
    },
    //添加选项卡  判断选项卡是否存在 如果不存在 就新增
    addTabs:function(text,href){
        if($('#tab').tabs('exists',text)){
            $('#tab').tabs('select',text)
        }else{
            $('#tab').tabs('add',{
                title: text,
                href: href,
                closable:true
            });
        }
    },
    //关闭选项卡
    closeTabs:function (title) {
       $('#tab').tabs('close',title);
    }

};



