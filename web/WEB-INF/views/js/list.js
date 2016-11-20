/**
 * Created by Administrator on 2016/4/8.
 */
var grid;
BUI.use(['common/search','common/page','bui/grid','bui/overlay','bui/form'],function (Search,Page,Grid,Overlay,Form) {

    var  search = new Search({
            store : store,
            gridCfg : gridCfg
        });

    grid = search.get('grid');
    //监听事件，删除一条记录
    grid.on('cellclick',function(ev){
        var sender = $(ev.domTarget); //点击的Dom
        if(sender.hasClass('btn-del')){
            var record = ev.record;
            delItems([record]);
        }
    });
});

//删除操作
function delFunction(){
    var selections = grid.getSelection();
    delItems(selections);
}

function delItems(items){
    var ids = [];
    BUI.each(items,function(item){
        ids.push(item.id);
    });
    if(ids.length){
        BUI.Message.Confirm('确认要删除选中的记录么？',function(){
            $.ajax({
                url :del_url,
                type : 'post',
                data : {ids : ids},
                success : function(data){
                    if(data.success){ //删除成功
                        search.load();
                    }else{ //删除失败
                        BUI.Message.Alert('删除失败！');
                    }
                }
            });
        },'question');
    }
}
