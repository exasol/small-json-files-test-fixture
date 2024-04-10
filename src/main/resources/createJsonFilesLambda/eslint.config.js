import globals from 'globals'

import { FlatCompat } from '@eslint/eslintrc'
import pluginJs from '@eslint/js'

const compat = new FlatCompat({ recommendedConfig: pluginJs.configs.recommended })

export default [
    { languageOptions: { globals: globals.browser } },
    ...compat.extends('standard'),
    {
        rules: {
            indent: ['error', 4],
            'space-before-function-paren': ['error', 'never'],
            semi: ['off']
        }
    }
]
