####项目简介
- 该项目是一个简单的表格View
- 仅仅是提供了表格的绘制

---
表格控件 **TableView**

![tableView](https://github.com/yudapiandroid/tableview/blob/master/images/table_view.jpg)
图中红色标出的一个方块称为一个 cell

自定义属性
- 背景颜色 bgColor
- 分割线颜色 borderColor
- 分割线大小 borderWidth
- cell宽度 pWidth
- cell高度 pHeight
- 需要显示的行数 row

```xml
<com.suse.dapi.tableview.core.view.TableView
        app:bgColor="#fff"
        app:borderColor="#aaa"
        app:borderWidth="1dp"
        app:pHeight="20dp"
        app:pWidth="20dp"
        app:row="10"
        android:layout_width="match_parent"
        android:layout_height="200dp" />
```

---
可以水平滚动的表格控件 **HScrollTableView**
这个的自定义属性和上面的一样只是前面加了一个 h_

```xml
<com.suse.dapi.tableview.core.view.HScrollTableView
        android:id="@+id/table"
        app:h_pWidth="20dp"
        app:h_pHeight="20dp"
        app:h_borderWidth="1dp"
        app:h_borderColor="#aaa"
        app:h_bgColor="#fff"
        app:h_row="10"
        android:layout_width="match_parent"
        android:layout_height="207dp">
    </com.suse.dapi.tableview.core.view.HScrollTableView>
```

---
自定义绘制的内容
通过实现 DrawLayer 来实现内容的绘制
```java
public interface DrawLayer {
    void draw(List<Rect> cells,List<Object> data, Canvas canvas);
}
```

- cells 参数是table中所有cell的集合，类型是Rect包含了当前cell的边界
- data 参数是使用者自己的数据
- canvas 是回调onDraw的时候的canvas

有一个自带的实现，DrawBitmapLayer

---
使用说明

配置仓库地址
```xml
allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
```

添加依赖
```xml
dependencies {
	        compile 'com.github.yudapiandroid:tableview:0.0.1'
	}
```

声明控件
```xml
<com.suse.dapi.tableview.core.view.HScrollTableView
        android:id="@+id/table"
        app:h_pWidth="20dp"
        app:h_pHeight="20dp"
        app:h_borderWidth="1dp"
        app:h_borderColor="#aaa"
        app:h_bgColor="#fff"
        app:h_row="10"
        android:layout_width="match_parent"
        android:layout_height="207dp">
    </com.suse.dapi.tableview.core.view.HScrollTableView>
```

绑定数据
```java
HScrollTableView tableView = (HScrollTableView) findViewById(R.id.table);
List<Object> data = new ArrayList<>();
tableView.setData(data);
tableView.addDrawLayer(new DrawBitmapLayer(
         BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher),
         2
));
tableView.notifyDataSetChange();
```
