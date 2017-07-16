package com.domker.db.annotation

import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import javax.lang.model.element.Element
import javax.lang.model.element.Modifier
import javax.lang.model.util.Elements

/**
 * Created by Maison on 2017/7/16.
 */
class FragmentAnnotatedClass(val elemment: Element) {

    fun generator(): JavaFile {


        val method = MethodSpec.methodBuilder("getFragment")
                .addModifiers(mutableListOf(Modifier.PUBLIC, Modifier.STATIC))
                .addParameter(TypeName.INT, "type")
//                .returns(TypeName.get(IFragment::class.java))
                .returns(TypeName.VOID)

        val typeClass = TypeSpec.classBuilder("FragmentBuilder")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(method.build())
                .build()

        val packageName = "com.domker.db.fragment"
        return JavaFile.builder(packageName, typeClass).build()
    }

}