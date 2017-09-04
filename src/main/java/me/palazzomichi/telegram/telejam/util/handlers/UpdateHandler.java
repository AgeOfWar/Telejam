package me.palazzomichi.telegram.telejam.util.handlers;

import me.palazzomichi.telegram.telejam.objects.updates.Update;

import java.util.function.Consumer;

/**
 * Represents an operation that accepts an update and returns no
 * result. Used usually to handle updates received from a bot.
 *
 * @author Michi Palazzo
 */
public interface UpdateHandler extends Consumer<Update> {
}
