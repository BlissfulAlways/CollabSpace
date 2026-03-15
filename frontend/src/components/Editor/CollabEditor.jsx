import { useEditor, EditorContent } from '@tiptap/react'
import StarterKit from '@tiptap/starter-kit'
import Collaboration from '@tiptap/extension-collaboration'
import * as Y from 'yjs'
import  { useMemo } from 'react'

const CollabEditor = () => {
    const ydoc = useMemo(() => new Y.Doc(), [])

    const editor = useEditor({
        extensions : [
            StarterKit.configure({
                history : false
            }),
            Collaboration.configure({
                document : ydoc
            }),
        ],
        content : ''
    })

    return (
        <div>
            <EditorContent editor={editor} />
        </div>
    )
}
export default CollabEditor
