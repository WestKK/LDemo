	
	layui.use('laydate', function() {
		var laydate = layui.laydate;
		//日期时间选择器
		laydate.render({
			elem : '#tMsg',
			type : 'datetime'
		});

	});
	
	
	
	
	function getDelete(e) {
		layer.confirm('是否删除?', {
			btn : [ '是的', '取消' ]
		//按钮
		}, function() {
			$.ajax({
				url : "/LoginDemo/message/del/"
						+ $(e).parents("tr").find("td").eq(0).text(),
				type : "delete",
				//dataType:"json",
				success : function(data) {
					if (data == "true") {
						layer.alert('删除成功', {
							skin : 'layui-layer-lan',
							closeBtn : 0,
							anim : 6,
							time : 3000,
							end : function() {
								location.reload();
							}
						//动画类型
						});
					} else {
						layer.alert('删除失败', {
							skin : 'layui-layer-lan',
							closeBtn : 0,
							anim : 6,
							time : 3000,
							end : function() {
								location.reload();
							}
						//动画类型
						});
					}
				}
			});
		});
	};

	$("#modal")
			.click(
					function() {
						$
								.ajax({
									url : "/LoginDemo/message/findName",
									type : "get",
									data : {
										senderName : $("#senderName").val(),

									},
									success : function(data) {
										var info = "";
										$
												.each(
														data,
														function(index, u) {
															//girl 其实就是集合中的每个对象名			
															info += "<option value=\"" + u.userName + "\" >"
																	+ u.userName
																	+ "</option>";
														});
										$("#receiveName").html(info);
									}
								});
					});

	$("#send").click(function() {
		$.ajax({
			url : "/LoginDemo/message/addMsg",
			type : "post",
			data : {
				senderName : $("#senderName").val(),
				receiveName : $("#receiveName").val(),
				content : $("#content").val(),
			},
			success : function(data) {
				if (data == "true") {
					layer.alert('发送成功', {
						skin : 'layui-layer-lan',
						closeBtn : 0,
						anim : 4,
						time : 3000,
						end : function() {
							location.reload();
						}
					//动画类型
					});
				} else {
					layer.alert('发送失败', {
						skin : 'layui-layer-lan',
						closeBtn : 0,
						anim : 4,
						time : 3000,
						end : function() {
							location.reload();
						}
					//动画类型
					});
				}
			}
		});
	});

	function getChange(e) {
		var InfoState = $(e).parents("tr").find("td").eq(5).text();
		if (InfoState == "未读") {
			$.ajax({
				url : "/LoginDemo/message/change/"
						+ $(e).parents("tr").find("td").eq(0).text(),
				type : "put",
				contentType : "application/json",
				data : JSON.stringify({
					"state" : "1"
				}),
				success : function(data) {
					window.location.reload();
				}
			});
		} else {
			$.ajax({
				url : "/LoginDemo/message/change/"
						+ $(e).parents("tr").find("td").eq(0).text(),
				type : "put",
				contentType : "application/json",
				data : JSON.stringify({
					"state" : "0"
				}),
				success : function(data) {
					window.location.reload();
				}
			});
		}
	};

	function updateInfo(e) {
		var content = $(e).parents("tr").find("td").eq(3).text();
		var time = $(e).parents("tr").find("td").eq(4).text();
		$("#cMsg").val(content);
		$("#tMsg").val(time);
		$("#myModal").modal("show");
		$("#change")
				.click(
						function() {
							$
									.ajax({
										url : "/LoginDemo/message/update/"
												+ $(e).parents("tr").find(
														"td").eq(0).text(),
										type : "put",
										contentType : "application/json",
										data : JSON.stringify({
											"content" : $("#cMsg").val(),
											"time" : $("#tMsg").val(),
										}),
										success : function(data) {
											$("#myModal").modal("hide");
											if (data == true) {
												layer
														.alert(
																'修改成功',
																{
																	skin : 'layui-layer-lan',
																	closeBtn : 0,
																	anim : 4,
																	time : 3000,
																	end : function() {
																		location
																				.reload();
																	}
																//动画类型
																});
											} else {
												layer
														.alert(
																'修改失败',
																{
																	skin : 'layui-layer-lan',
																	closeBtn : 0,
																	anim : 4,
																	time : 3000,
																	end : function() {
																		location
																				.reload();
																	}
																//动画类型
																});
											}
										}
									});
						});
	};
	
	
	
	
	
	
	
	function showImage() {
		var docObj = document.getElementById("doc");
		var imgObjPreview = document.getElementById("preview");
		if (docObj.files && docObj.files[0]) {
			//火狐下，直接设img属性  
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '300px';
			imgObjPreview.style.height = '120px';
			//imgObjPreview.src = docObj.files[0].getAsDataURL();  
			//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式  
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		} else {
			//IE下，使用滤镜  
			docObj.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("localImag");
			//必须设置初始大小  
			localImagId.style.width = "250px";
			localImagId.style.height = "200px";
			//图片异常的捕捉，防止用户修改后缀来伪造图片 
			try {
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				localImagId.filters
						.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			} catch (e) {
				alert("您上传的图片格式不正确，请重新选择!");
				return false;
			}
			imgObjPreview.style.display = 'none';
			document.selection.empty();
		}
		return true;
	}

	function upClick() {
		$("#myModal03").modal("show");
	}