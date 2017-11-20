<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="easyui-panel" title="商品规格参数模板详情" data-options="fit:true">
    <form class="form" id="itemParamAddForm" name="itemParamAddForm" method="post">
        <table style="width:100%;">
            <tr>
                <td class="label">商品类目：</td>
                <td>
                    <input id="cid" name="cid" style="width:200px;">
                </td>
            </tr>
            <tr>
                <td class="label">规格参数：</td>
                <td>
                    <button class="easyui-linkbutton" onclick="addGroup()" type="button" data-options="iconCls:'icon-add'">添加分组</button>
                    <ul id="item-param-group">

                    </ul>
                    <ul id="item-param-group-template" style="display:none;">
                        <li>
                            <input name="group">
                            <button title="添加参数" class="easyui-linkbutton" onclick="addParam(this)" type="button" data-options="iconCls:'icon-add'"></button>
                            <button title="删除分组" class="easyui-linkbutton" onclick="delGroup(this)" type="button" data-options="iconCls:'icon-cancel'"></button>
                            <ul class="item-param">
                                <li>
                                    <input name="param">
                                    <button title="删除参数" class="easyui-linkbutton" onclick="delParam(this)" type="button" data-options="iconCls:'icon-cancel'"></button>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button class="easyui-linkbutton" onclick="submitForm()" type="button" data-options="iconCls:'icon-ok'">保存</button>
                    <button class="easyui-linkbutton" onclick="clearForm()" type="button" data-options="iconCls:'icon-undo'">重置</button>
                </td>
            </tr>
        </table>
    </form>
</div>
<script>
    //提交
    function submitForm() {
        var groupValues=[];
        //遍历分组
        var $groups=$('#item-param-group [name=group]');
        $groups.each(function (index,ele) {//组
            console.log("index"+index);//索引
            console.log('ele'+ele);//对象
           //遍历分组项
            var paramValues=[];
            var $params=$(ele).parent().find('.item-param [name=param]');
            $params.each(function (_index,_ele) {//参数
              //  console.log("_index"+index);//索引
                // console.log('_ele'+ele);//对象
                var _val=$(_ele).val();//获得参数的值
                //console.log('_ele'+_val);
                if ($.trim(_val).length>0){//trim去掉字符串起始和结尾的空格。
                    paramValues.push(_val)
                }
            });
            var val=$(ele).val();//获得组的值
            var o={};
            o.group=val;
            o.params=paramValues;
            if ($.trim(val).length>0&&paramValues.length>0){
                groupValues.push(o);
            }
        });
        //得到规格参数模版的json串
        console.log("++"+groupValues);
        var cid=$('#cid').combotree('getValue');//获取组件值的数组。
        var url='paramSave/'+cid;
        var jsonStr=JSON.stringify(groupValues);
        $.post(
            url,
        {paramData:jsonStr},
            function (data) {
                alert(data);
                console.log('success');
                if (data>0){
                    wwshop.addTabs('规格参数','item-param-list');
                    wwshop.closeTabs('新增商品规格模版');
                }else {
                    $.messager.alert("警告","出错!!!");
                }
            }

        )

    }

    //删除参数
    function delParam(ele) {
        $(ele).parent().remove();
    }
    //删除分组
    function delGroup(ele){
        $(ele).parent().remove();
    }
    //添加参数
    function addParam(ele) {
       // console.log("参数"+ele);
        var $paramli=$('#item-param-group-template .item-param li').eq(0).clone();

        $(ele).parent().find('.item-param').append($paramli);
        console.log($(ele).parent().find('.item-param-group')) ;
    }
    //添加分组
    function addGroup() {
        //eq（0）是个集合
        var $li = $('#item-param-group-template li').eq(0).clone();
        $('#item-param-group').append($li);//添加节点

    }
    
    //商品类目
    $("#cid").combotree({
        url:'itemCats?parentId=0',
        required:true,
        onBeforeExpand:function (node) {
            console.log(node);
            //获取当前被点击的tree
            var $currentTree=$("#cid").combotree('tree');
            //调用tree的options方法
            var option=$currentTree.tree('options');
            //修改url的options属性
            option.url="itemCats?parentId="+node.id;

        },
        onBeforeSelect:function (node) {
            //判断选中点是否为叶子 节点  如果是返回true
            var isLeaf=$("#cid").tree('isLeaf',node.target);
            //不是给出警告窗
            if (!isLeaf){
                $.messager.alert('警告','请选择最终的类目!!!');
                return false;
            }

        }
    })
</script>