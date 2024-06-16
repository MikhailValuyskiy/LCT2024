package ru.androidheroes.kamchatka.ui

//class SearchBar @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null,
//    defStyle: Int = 0
//) : FrameLayout(context, attrs, defStyle) {
//
//    private val editText: EditText by lazy { search_edit_text }
//
//    private var hint: String = ""
//    private var isCancelVisible: Boolean = true
//
//    init {
//        LayoutInflater.from(context).inflate(R.layout.search_toolbar, this)
//        if (attrs != null) {
//            context.obtainStyledAttributes(attrs, R.styleable.SearchBar).apply {
//                hint = getString(R.styleable.SearchBar_hint).orEmpty()
//                isCancelVisible = getBoolean(R.styleable.SearchBar_cancel_visible, true)
//                recycle()
//            }
//        }
//    }
//
//    fun setText(text: String?) {
//        this.editText.setText(text)
//    }
//
//    fun clear() {
//        this.editText.setText("")
//    }
//
//    override fun onFinishInflate() {
//        super.onFinishInflate()
//        search_edit_text.hint = hint
//        delete_text_button.setOnClickListener {
//            search_edit_text.text.clear()
//        }
//        delete_text_button.visibility = View.VISIBLE
//    }
//
//    override fun onAttachedToWindow() {
//        super.onAttachedToWindow()
//        search_edit_text.afterTextChanged { text ->
//
//        }
//    }
//}
