<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="tb">
    <div>
        <button type="button" onclick="addParam()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</button>
        <button type="button" onclick="editParam()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</button>
        <button type="button" onclick="deleteParam()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</button>
    </div>
</div>
<table id="dgParamList"></table>
<script>
    //新增分组
    function  addParam() {
        wwshop.addTabs('新增商品规格模版','item-param-add');
    }

    //初始化数据表格
    $('#dgParamList').datagrid({
        title:'商品规格模版列表',
        url:'itemParams',
        fit: true,
        //如果为true，则显示一个行号列。
        rownumbers: true,
        //如果为true，则在DataGrid控件底部显示分页工具栏。
        pagination: true,
        pageSize:10,
        toolbar: '#tb',
        columns:[[
            {field:'ck',checkbox:true},
            {field:'id',title:'ID',sortable:true},
            {field:'itemCatName',title:'商品类目'},
            {field:'paramData',title:'规格(只显示分组名称)',formatter:function (value,row,index) {
               // console.log(value);
                //alert(typeof (value)); string类型
               // console.log(row);  objectl类型
                //string转为json
                var obj=JSON.parse(value);
                var arr=[];
                $.each(obj,function (i,e) {
                   /* console.group();
                    //console.log(i);//索引
                    console.log(e);//对象
                    console.groupEnd();*/
                 //  console.log(e.group);
                   arr.push(e.group);//push() 方法可向数组的末尾添加一个或多个元素，并返回新的长度。

                })

                return arr;
            }},
            {field:'created',title:'创建日期', formatter:function(value,row,index){
                return moment(value).format('YYYY年MM月DD日,hh:mm:ss');
            }},
            {field:'updated',title:'更新日期', formatter:function(value,row,index){
                return moment(value).format('YYYY年MM月DD日,hh:mm:ss');
            }}

        ]]


    });
</script>