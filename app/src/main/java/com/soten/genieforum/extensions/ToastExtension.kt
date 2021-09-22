package com.soten.genieforum.extensions

import android.widget.Toast
import com.soten.genieforum.GenieForumApplication.Companion.appContext

fun toast(message: String) {
    Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show()
}

object ToastMessage {

    const val FAIL_LOGIN = "로그인 실패"
    const val SUCCESS_LOGIN = "로그인 성공"
    const val FAIL_REGISTER_USER = "회원가입 실패"

    const val INVALID_USER = "아이디나 비밀번호가 틀렸습니다."
    const val INVALID_INPUT = "입력값이 잘못됐습니다."

    const val WELCOME_COMMENT = "환영합니다"

    const val INVALID_LOGIN = "로그인 정보가 유효하지 않습니다."

    const val NOT_CURRENT_USER = "로그인 후 이용해 주세요"
}