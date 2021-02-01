/*
 * Copyright (c) 2020-2021 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/GeyserDiscordBot
 */

package org.geysermc.discordbot.storage;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;

import java.util.List;

public abstract class AbstractStorageManager {

    /**
     * Do any initial setup for the storage when the bot starts
     */
    public abstract void setupStorage();

    /**
     * Close any connections or do clean-up
     * TODO: Call this on shutdown
     */
    public abstract void closeStorage();

    /**
     * Get a preference from the database
     *
     * @param serverID Guild ID to get the preference for
     * @param preference Key of the requested preference
     * @return The string value of the preference
     */
    public abstract String getServerPreference(long serverID, String preference);

    /**
     * Set a preference in the database
     *
     * @param serverID Guild ID to set the preference for
     * @param preference Key of the preference to change
     * @param value Value to set the preference to
     */
    public abstract void setServerPreference(long serverID, String preference, String value);

    /**
     * Store a persistent role in the database
     *
     * @param member {@link Member} to store the role for
     * @param role {@link Role} to store for the {@link Member}
     */
    public abstract void addPersistentRole(Member member, Role role);

    /**
     * Remove a persistent role from the database
     *
     * @param member {@link Member} to remove the role for
     * @param role {@link Role} to remove for the {@link Member}
     */
    public abstract void removePersistentRole(Member member, Role role);

    /**
     * Get a list of persistent roles for a {@link Member}
     *
     * @param member The Guild {@link Member} to get persistent roles for
     * @return List of the Guilds {@link Role} marked as persistent for the {@link Member}
     */
    public abstract List<Role> getPersistentRoles(Member member);

    /**
     * Log a moderation event to the database
     *
     * @param user Guild {@link Member} that created the event
     * @param action The action that was done
     * @param target The {@link User} that was targeted
     * @param reason The reason for this if specified
     */
    public abstract void addLog(Member user, String action, User target, String reason);

    /**
     * Get the moderation log for a user
     *
     * @param guild Guild to get the log for
     * @param target The {@link User} that was targeted
     * @param limit The limit of results to get
     * @return List of {@link ModLog} returned
     */
    public abstract List<ModLog> getLog(Guild guild, User target, int limit);

    /**
     * Calls {@link AbstractStorageManager#getLog(Guild, User, int)} with the default limit of 5
     *
     * @see AbstractStorageManager#getLog(Guild, User, int)
     */
    public List<ModLog> getLog(Guild guild, User target) {
        return getLog(guild, target, 5);
    }

    public abstract LevelInfo getLevel(Member user);

    public abstract void setLevel(Member user, LevelInfo levelInfo);
}