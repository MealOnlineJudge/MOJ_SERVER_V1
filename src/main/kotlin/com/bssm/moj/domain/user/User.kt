package com.bssm.moj.domain.user

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import team.untitled.unboxingBackend.domain.user.authority.Authority

@Entity
class User(
    id: Long,
    name: String,
    email: String,
    profileImage: String,
    authority: Authority,
    nickname: String,
    enroll: Int,
    grade: Int,
    class_number: Int,
    student_number: Int,
) {

    @Id
    val id: Long = id

    var name: String = name
        protected set

    var email: String = email
        protected set

    var profileImage: String = profileImage
        protected set

    @Enumerated(EnumType.STRING)
    var authority: Authority = authority
        protected set

    var nickname: String = nickname
        protected set

    var enroll: @Min(2021) Int = enroll
        protected set

    var grade: @Min(1)  @Max(3) Int = grade
        protected set

    var class_number: @Min(1)  @Max(4) Int = class_number
        protected set

    var student_number: @Min(1)  @Max(16) Int = student_number
        protected set
}
