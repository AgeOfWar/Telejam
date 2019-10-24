package io.github.ageofwar.telejam.examples.limitatibot.utils

import io.github.ageofwar.telejam.messages.Message

interface AskMessage {

    fun onInputReceived(message: Message)

}