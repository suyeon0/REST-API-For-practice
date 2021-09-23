var token = $("input[name='_csrf']").val();
var header = "X-CSRF-TOKEN";

// login 데이터 ajax 전송
function loginDataSend() {
    var email = $('#email').val();
    var pwd = $('#pwd').val();
    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        type: "post",
        url: "/user2/login",
        data: { email: email, pwd: pwd },
        success: function (res) {
            loginDataSendSuccess(res)
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

// login ajax success 이후 처리
function loginDataSendSuccess(res) {
    if (res == 1) {
        alert("USER_INCORRECT_EMAIL");
        return false;
    } else if (res == 2) {
        alert("USER_INCORRECT_PWD");
        return false;
    } else if (res == 9) {
        alert("server error");
    } else {
        window.location.href = "/";
    }
}