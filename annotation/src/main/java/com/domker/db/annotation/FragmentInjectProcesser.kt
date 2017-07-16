package com.domker.db.annotation

import com.google.auto.service.AutoService
import java.util.*
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.annotation.processing.Filer



/**
 * Created by Maison on 2017/7/16.
 */
@AutoService(Processor::class)
class FragmentInjectProcesser : AbstractProcessor() {
    private lateinit var element: Elements
    private lateinit var mFiler: Filer

    override fun init(env: ProcessingEnvironment?) {
        super.init(env)
        element = env!!.elementUtils
        mFiler = env!!.filer
    }

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        processFragmentShard(roundEnv)
        return true
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        val set = HashSet<String>()
        set.add(FragmentShard::class.simpleName!!)
        return set
    }

    fun processFragmentShard(roundEnv: RoundEnvironment?) {
        if (roundEnv != null) {
            roundEnv.getElementsAnnotatedWith(FragmentShard::class.java)
                    .map { FragmentAnnotatedClass(it) }
                    .forEach { it.generator().writeTo(mFiler) }
        }
    }
}