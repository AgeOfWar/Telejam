package io.github.ageofwar.telejam.examples.limitatibot.utils

import io.github.ageofwar.telejam.messages.Message
import io.github.ageofwar.telejam.messages.MessageHandler
import io.github.ageofwar.telejam.users.User
import java.util.HashMap

class InputListener : MessageHandler {

    companion object {
        private val usersToListen = HashMap<User, AskMessage>()

        fun listenFor(user: User, askMessage: AskMessage) {
            usersToListen[user] = askMessage
        }

        fun removeUser(user: User) {
            usersToListen.remove(user)
        }
    }

    override fun onMessage(message: Message) {
        val askMessage = usersToListen[message.sender] ?: return

        askMessage.onInputReceived(message)
        usersToListen.remove(message.sender)
    }

}