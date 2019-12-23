

LoginClass = {
		validateLogin : function() {

			var uname = $("#uname").val();
			var pwd = $("#pwd").val();
			if (uname == "") {
				alert("Kindly enter Username");
				return false;
			}
			if (pwd == "") {
				alert("Kindly enter Password");
				return false;
			}

			if (uname != "" && pwd != "") {
				$("#btnSbm").click();
			}

		}
                
                
                
	}