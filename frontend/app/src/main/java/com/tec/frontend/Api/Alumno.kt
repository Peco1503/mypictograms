package com.tec.frontend.Api

data class Alumno(val id : Int? = null,
                  val name : String? = null,
                  val birthYear : Int? = null,
                  val gender : String? = null,
                  val parentId : Int? = null,
                  val maximumMinigameLevel : Int? = null,
                  val description : String? = null,
                  val cognitiveLevel : String? = null,
                  val therapistId : Int? = null)
