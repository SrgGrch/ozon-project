package tech.blur.coreui.recycler

interface AdapterItem {
    /**
    Provides unique tag for an adapter item.

    If two adapter items share the same tag they are considered to be the same.
    In this case they are compared using equals which in turn helps to determine
    if the item has changed since last time it's been added to adapter.
     */
    val uniqueTag: String
}