var Login = {
    Element: {
        getMainfrom: function () {
            return $("#mainform");
        },
        getSubmitButton: function () {
            return $("#submitButton");
        },
        getPassword: function () {
            return $("#inputPassword");
        },
        getModalTitle: function () {
            return $("#modalTitle");
        },
        getModalContent: function () {
            return $("#modalContent");
        },
        getMyModal: function () {
            return $('#myModal');
        },
        getValicode: function () {
            return $("#valicode");
        },
        getUsername: function () {
            return $("#username")
        },
        getValiCodeImg: function () {
            return $("img[class='validateCode']");
        }
    },
    Event: {
        bindSubmitForm: function () {
            Login.Element.getSubmitButton().bind("click", function () {
                if (Login.Element.getValicode().val() == '') {
                    Login.Util.setContent(Login.Element.getModalTitle(), Login.Message.modalTitleMessage());
                    Login.Util.setContent(Login.Element.getModalContent(), Login.Message.valicodeNullMessage());
                    Login.Util.modal();
                    return;
                }
                if (Login.Element.getUsername().val() == '') {
                    Login.Util.setContent(Login.Element.getModalTitle(), Login.Message.modalTitleMessage());
                    Login.Util.setContent(Login.Element.getModalContent(), Login.Message.usernameNullMessage());
                    Login.Util.modal();
                    return;
                }
                if (Login.Element.getPassword().val().length < 6 || Login.Element.getPassword().val().length >= 18) {
                    Login.Util.setContent(Login.Element.getModalTitle(), Login.Message.modalTitleMessage());
                    Login.Util.setContent(Login.Element.getModalContent(), Login.Message.passwordLengthErrorMessage());
                    Login.Util.modal();
                    return;
                }
                Login.Element.getPassword().val(sha256_digest(Login.Element.getPassword().val()));
                $.ajax({
                    type: "POST",
                    url: Login.Element.getMainfrom().attr("action"),
                    data: Login.Element.getMainfrom().serialize(),
                    success: function (result) {
                        if (result.success == null || result.success == true) {
                            window.location.href = result.data;
                        } else {
                            Login.Util.setContent(Login.Element.getModalTitle(), Login.Message.modalTitleMessage());
                            Login.Util.setContent(Login.Element.getModalContent(), result.message);
                            Login.Util.modal();
                        }
                    },
                    error: function () {
                        Login.Util.setContent(Login.Element.getModalTitle(), Login.Message.modalTitleMessage());
                        Login.Util.setContent(Login.Element.getModalContent(), Login.Message.ajaxErrorMessage());
                        Login.Util.modal();
                    }

                });
            });
        },
        afterModal: function () {
            Login.Element.getMyModal().on('hidden.bs.modal', function () {
                Login.Element.getPassword().val('');
                Login.Element.getValicode().val('');
                Login.Util.replaceValiCodeImg();
            })
        },
        valiCodeImgEvent: function () {
            Login.Element.getValiCodeImg().bind("click", Login.Util.replaceValiCodeImg);
        },
        pressEnter: function () {
            $(window).bind('keydown', function (event) {
                switch (event.keyCode) {
                    case 13:
                        Login.Element.getSubmitButton().click();
                        break;
                }
            })
        }
    },
    Util: {
        setContent: function (element, value) {
            element.text(value);
        },
        modal: function () {
            Login.Element.getMyModal().modal('show');
        },
        replaceValiCodeImg: function () {
            Login.Element.getValiCodeImg().attr("src","/validateCode?time=" + new Date().getTime())
        }
    },
    Message: {
        ajaxErrorMessage: function () {
            return "系统异常,请稍后再试!";
        },
        modalTitleMessage: function () {
            return "出错啦...";
        },
        usernameNullMessage: function () {
            return "用户名不能为空!"
        },
        passwordLengthErrorMessage: function () {
            return "请输入6-18位的密码!"
        },
        valicodeNullMessage: function () {
            return "请输入验证码!"
        }
    },
    Init: function () {
        Login.Event.bindSubmitForm();
        Login.Event.afterModal();
        Login.Event.valiCodeImgEvent();
        Login.Event.pressEnter();
    }
};

