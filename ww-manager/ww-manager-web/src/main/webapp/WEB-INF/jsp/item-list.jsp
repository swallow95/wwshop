<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="toolbar">
    <div style="padding: 5px; background-color: #fff;">
        <label>商品标题:</label>
        <input type="text" class="easyui-textbox" id="title">
        <label>商品状态:</label>
        <select class="easyui-combobox" id="status">
            <option value="0">全部</option>
            <option value="1">正常</option>
            <option value="2">下架</option>
        </select>
        <button  onclick="searchForm()" type="button" class="easyui-linkbutton">搜索</button>
    </div>
    <div>
            <%--iconCls:图片  plain boolean 为true时显示简洁效果。 --%>
            <button onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</button>
            <button onclick="edit()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</button>
            <button onclick="remove()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</button>
            <button onclick="down()" class="easyui-linkbutton" data-options="iconCls:'icon-down',plain:true">上架</button>
            <button onclick="up()" class="easyui-linkbutton" data-options="iconCls:'icon-up',plain:true">下架</button>
    </div>



</div>
<table id="dg"></table>
<script>
    $("#dg").datagrid({
        //定义是否允许多列排序
        multiSort:true,
        toolbar:'#toolbar',
        url:'items',
        //隔行变色，斑马线效果
        striped:true,
        //添加分页工具栏
        pagination:true,
        //每行的前面显示行号
        rownumbers:true,
        //使得数据表格自适应填充父容器
        fit:true,
        //默认显示10条，这样的话就显示20条
        pageSize:20,
        //分页列表
        pageList:[20,50,100],
        //列属性
        columns: [[
            //field title width列属性
            {field: 'ck', checkbox: true},
            {field: 'id', title: '商品编号', width: 100,sortable:true},
            {field: 'title', title: '商品名称', width: 100,sortable:true},
            {field: 'sellPoint', title: '卖点', width: 100},
            {field:'status',title:'状态',width:100,formatter:function(value,row,index){
                /*console.group();//组
                console.log(value);//字段值
                console.log(row);//行记录数据
                console.log(index);//行索引
                console.groupEnd();*/
                switch (value){
                    case 1:
                        return "正常";
                        break;
                    case 2:
                        return "下架";
                        break;
                    case 3:
                        return "删除";
                        break;
                    default:
                        return "未知";
                        break;
                }
            }},
            {field: 'catName', title: '分类名称', width: 100},
            {field: 'priceView', title: '价格', width: 100, align: 'right'},
            {field: 'created', title: '创建时间', width: 100,formatter:function(value,row,index){
                return moment(value).format('LL');
            }},
            {field: 'updated', title: '修改时间', width: 100,formatter:function (value,row,index) {
                return moment(value).format('LL');

            }}

        ]]
    });
    /*搜索*/
    function searchForm(){
        $('#dg').datagrid('load',{
            title: $('#title').val(),
            status:$('#status').combobox('getValue')
        });




    }
   /* 增加*/
    function add() {
        //console.log("add");
        wwshop.addTabs('新增商品','item-add');
    }
   /* 编辑*/
    function edit(){
        console.log('edit');
    }
   /* 删除*/
    function remove() {
        //console.log("remove")
        //返回所有被选中的行，当没有记录被选中的时候将返回一个空数组。
        var selections = $('#dg').datagrid('getSelections');
        //console.log(selections);
        if (selections.length==0){
            //客户没有选择记录
            $.messager.alert('提示', '请至少选中一条记录！');
            return;
        }
        //客户至少选择了一条记录
        //确认框，第一个参数为标题，第二个参数确认框的提示内容，第三参数是一个确认函数
        //function(r) 如果用户点击的是"确定"，那么r=true
        $.messager.confirm("确认","你确认想要删除记录吗？",function (r) {
            if(r){
                // alert("确认");
                //存放id的集合
                var ids=[];
                //遍历选中的记录，将记录的id存放到js数组中
                for(var i=0;i<selections.length;i++){
                    ids.push(selections[i].id);
                    // alert(selections[0].id);
                }
                //将ids异步提交到后台
                $.post(
                    //url:请求后台的哪个地址来进行处理。相当于url  string类型
                    'items/batch',
                    //data:从前台提交哪些数据给后台处理 相当于data object类型
                    {'ids[]':ids},
                    //callback:后台处理成功后回调函数，相当于success， function函数
                    function (data) {
                        // alert(data);
                        $("#dg").datagrid('reload');

                    },
                    //dataType:返回的数据类型。。如：json，String类型
                    'json'
                )
            }
        })
    }
   /* 上架*/
   function down() {
       //返回所有被选中的行，当没有记录被选中的时候将返回一个空数组。
       var selections=$("#dg").datagrid("getSelections");
       //没有选中
       if(selections.length==0){
           $.messager.alert("提示","至少选中一个商品！");return;
       }
       var ids=[];//遍历选中的记录，将记录的id存放到js数组中
       for(var i=0;i<selections.length;i++) {
           if (selections[i].status == 1) {
               $.messager.alert('提示', '该商品已上架！！');return;
           }else{
               ids.push(selections[i].id);
           }
       }
       //至少选择了一个
       $.messager.confirm("确认","你确认要上架吗？",function (r) {
           if (r){
               //异步请求
               $.post(
                   'items/upItems',
                   {'ids[]':ids},
                   function (data) {
                       //alert(data);
                       $('#dg').datagrid('reload');
                   },
                   'json'
               )
           }
       })
   }
   /* 下架*/
   function up() {
       var selections=$("#dg").datagrid('getSelections');
       console.log(selections);
       if(selections.length==0){
           $.messager.alert("提示",'至少选中一条记录');
           return;
       }
       var ids=[];
       for(var i=0;i<selections.length;i++){
           if (selections[i].status==2){
               $.messager.alert('提示','该商品已下架！');
               return;
           }else{
               ids.push(selections[i].id);
           }
       }
       $.messager.confirm("确认","你确认要下架吗？",function (r) {
           if (r){
               $.post(
                   'Items/ItemDown',
                   {'ids[]':ids},
                   function (data) {
                       // alert(data);
                       $("#dg").datagrid('reload');
                   },
                   'json'
               )
           }
       })
   }







    /*var toolbar=[{
        iconCls: 'icon-add',
        text: '新增',
        handler: function () {
            console.log('add');
        }
    },{
        iconCls:'icon-remove',
        text:'删除',
        handler:function () {
            //console.log("remove")
            //返回所有被选中的行，当没有记录被选中的时候将返回一个空数组。
            var selections = $('#dg').datagrid('getSelections');
            console.log(selections);
            if (selections.length==0){
                //客户没有选择记录
                $.messager.alert('提示', '请至少选中一条记录！');
                return;
            }
            //客户至少选择了一条记录
            //确认框，第一个参数为标题，第二个参数确认框的提示内容，第三参数是一个确认函数
            //function(r) 如果用户点击的是"确定"，那么r=true
            $.messager.confirm("确认","你确认想要删除记录吗？",function (r) {
                if(r){
                   // alert("确认");
                    //存放id的集合
                    var ids=[];
                    //遍历选中的记录，将记录的id存放到js数组中
                    for(var i=0;i<selections.length;i++){
                        ids.push(selections[i].id);
                       // alert(selections[0].id);
                    }
                    //将ids异步提交到后台
                    $.post(
                        //url:请求后台的哪个地址来进行处理。相当于url  string类型
                        'items/batch',
                        //data:从前台提交哪些数据给后台处理 相当于data object类型
                        {'ids[]':ids},
                        //callback:后台处理成功后回调函数，相当于success， function函数
                        function (data) {
                           // alert(data);
                            $("#dg").datagrid('reload');

                        },
                        //dataType:返回的数据类型。。如：json，String类型
                        'json'
                    )
                }
            })

        }
    },{
            iconCls: 'icon-edit',
            text: '编辑',
            handler: function () {
                console.log('edit');
            }
        }, {
            iconCls: 'icon-up',
            text: '上架',
            handler: function () {
                //返回所有被选中的行，当没有记录被选中的时候将返回一个空数组。
               var selections=$("#dg").datagrid("getSelections");
               //alert(selections);
               console.log(selections);
               //没有选中
               if(selections.length==0){
                   $.messager.alert("提示","至少选中一个商品！");
                   return;
               }
                var ids=[];
                //遍历选中的记录，将记录的id存放到js数组中
                for(var i=0;i<selections.length;i++) {
                    if (selections[i].status == 1) {
                        $.messager.alert('提示', '该商品已上架！！');
                        return;
                    }else{
                        ids.push(selections[i].id);
                    }
                }
               //alert( $("#status").val());
               //至少选择了一个
               $.messager.confirm("确认","你确认要上架吗？",function (r) {
                   if (r){
                       //异步请求
                       $.post(
                           'items/upItems',
                           {'ids[]':ids},
                           function (data) {
                               //alert(data);
                               $('#dg').datagrid('reload');
                           },
                           'json'
                       )

                   }

               })

            }
        }, {
            iconCls: 'icon-down',
            text: '下架',
            handler: function () {
               // console.log('down');
                var selections=$("#dg").datagrid('getSelections');
                console.log(selections);
                if(selections.length==0){
                    $.messager.alert("提示",'至少选中一条记录');
                    return;
                }
                var ids=[];
                for(var i=0;i<selections.length;i++){
                    if (selections[i].status==2){
                        $.messager.alert('提示','该商品已下架！');
                        return;
                    }else{
                        ids.push(selections[i].id);
                    }
                }
                $.messager.confirm("确认","你确认要下架吗？",function (r) {
                    if (r){




                        $.post(
                            'Items/ItemDown',
                            {'ids[]':ids},
                            function (data) {
                               // alert(data);
                                $("#dg").datagrid('reload');
                            },
                            'json'
                        )
                    }
                })
            }
    }]*/
</script>
