layui.use('form', function() {
	var form = layui.form;

	var province1 = $("#province1"),
		city1 = $("#city1"),
		district1 = $("#district1");
	
	//初始将省份数据赋予
	for (var i = 0; i < provinceList.length; i++) {
		addEle(province1, provinceList[i].name);
	}
	
	//赋予完成 重新渲染select
	form.render('select');
	
	//向select中 追加内容
	function addEle(ele, value) {
		var optionStr1 = "";
		optionStr1 = "<option value=" + value + " >" + value + "</option>";
		ele.append(optionStr1);
	}

	//移除select中所有项 赋予初始值
	function removeEle(ele) {
		ele.find("option").remove();
		var optionStar1 = "<option value=" + "0" + ">" + "请选择" + "</option>";
		ele.append(optionStar1);
	}

	var provinceText1,
		cityText1,
		cityItem1;
	
	//选定省份后 将该省份的数据读取追加上
	form.on('select(province1)', function(data) {
		provinceText1 = data.value;
		$.each(provinceList, function(i, item) {
			if (provinceText1 == item.name) {
				cityItem1 = i;
				return cityItem1;
			}
		});
		removeEle(city1);
		removeEle(district1);
		$.each(provinceList[cityItem1].cityList, function(i, item) {
			addEle(city1, item.name);
		})
		//重新渲染select 
		form.render('select');
	})

	////选定市或直辖县后 将对应的数据读取追加上
	form.on('select(city1)', function(data) {
		cityText1 = data.value;
		removeEle(district1);
		$.each(provinceList, function(i, item) {
			if (provinceText1 == item.name) {
				cityItem1 = i;
				return cityItem1;
			}
		});
		$.each(provinceList[cityItem1].cityList, function(i, item) {
			if (cityText1 == item.name) {
				for (var n = 0; n < item.areaList.length; n++) {
					addEle(district1, item.areaList[n]);
				}
			}
		})
		//重新渲染select 
		form.render('select');
	})



})