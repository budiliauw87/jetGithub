package com.liau.jetgithub.util

import com.liau.jetgithub.core.data.local.entity.User
import com.liau.jetgithub.core.data.network.response.Followers
import com.liau.jetgithub.core.data.network.response.Following
import com.liau.jetgithub.core.data.network.response.Node
import com.liau.jetgithub.core.data.network.response.Repositories

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

        fun mappingNodeToUser(nodeItem: Node?): User {
            return User(
                login = nodeItem?.login ?: "Null",
                name = (nodeItem?.name ?: "Null") as String,
                location = (nodeItem?.location ?: "Null") as String,
                email = nodeItem?.email ?: "Null",
                company = (nodeItem?.company ?: "Null") as String,
                avatarUrl = nodeItem?.avatarUrl ?: "",
                follower = nodeItem?.followers?.totalCount ?: 0,
                following = nodeItem?.following?.totalCount ?: 0,
                repositories = nodeItem?.repositories?.totalCount ?: 0,
            )
        }

        fun mappingUserToNode(user: User?): Node {
            val asd = Following(totalCount = user?.follower ?: 0)
            return Node(
                login = user?.login ?: "Null",
                name = (user?.name ?: "Null") as String,
                location = (user?.location ?: "Null") as String,
                email = user?.email ?: "Null",
                company = (user?.company ?: "Null") as String,
                avatarUrl = user?.avatarUrl ?: "",
                followers = Followers(totalCount = user?.follower ?: 0),
                following = Following(totalCount = user?.following ?: 0),
                repositories = Repositories(totalCount = user?.repositories ?: 0),
            )
        }
    }

}