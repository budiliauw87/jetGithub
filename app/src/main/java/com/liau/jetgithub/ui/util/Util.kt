package com.liau.jetgithub.ui.util

/**
 * Created by Budiman on 26/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
abstract class Util {
    companion object {
        fun getQueryGraph(query: String, lastCursor: String, method: Int): String {
            val resultQueryGraph: String
            val querySearch: String
            val cursorAfter: String
            when (method) {
                0 -> {
                    querySearch = if (query.isEmpty()) "language:java" else query
                    cursorAfter = if (lastCursor.isEmpty()) "" else ",after:\"$lastCursor\""
                    resultQueryGraph =
                        "query { search( query: \"" + querySearch + "\", type: USER, first:10" + cursorAfter + ") {userCount edges { node { ... on User { id login name location email company avatarUrl followers " + "{ totalCount } following { totalCount } repositories { totalCount }}}cursor}}}"
                }
                1 -> {
                    cursorAfter = if (lastCursor.isEmpty()) "" else ",after:\"$lastCursor\""
                    resultQueryGraph =
                        "query { user( login: \"" + query + "\" ) { followers( first:10" + cursorAfter + " ) { edges{ node{ ... on User { id login name location email company avatarUrl followers " + "{ totalCount } following { totalCount } repositories{ totalCount }}} cursor}}}}"
                }
                2 -> {
                    cursorAfter = if (lastCursor.isEmpty()) "" else ",after:\"$lastCursor\""
                    resultQueryGraph =
                        "query { user( login: \"" + query + "\" ) { following( first:10" + cursorAfter + " ) { edges{ node{ ... on User { id login name location email company avatarUrl followers " + "{ totalCount } following { totalCount } repositories{ totalCount }}} cursor}}}}"
                }
                else -> resultQueryGraph = ""
            }
            return resultQueryGraph
        }
    }
    
}