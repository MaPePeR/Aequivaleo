package com.ldtteam.aequivaleo.api.compound.information.validity;

import com.ldtteam.aequivaleo.api.IAequivaleoAPI;
import com.ldtteam.aequivaleo.api.compound.ICompoundType;
import com.ldtteam.aequivaleo.api.compound.container.ICompoundContainer;
import com.ldtteam.aequivaleo.api.recipe.equivalency.IEquivalencyRecipeRegistry;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.BiFunction;

/**
 * This registry holds callbacks that indicate to the analysis engine if a given compound is allowed to exists on a
 * given game object or its wrapper.
 */
public interface IValidCompoundTypeInformationProviderRegistry
{

    /**
     * Gives access to the current instance of the valid information provider registry.
     *
     * @param worldKey The key for the world for which the instance is retrieved.
     *
     * @return The valid information provider registry.
     */
    static IValidCompoundTypeInformationProviderRegistry getInstance(@NotNull final RegistryKey<World> worldKey) {
        return IAequivaleoAPI.getInstance().getValidCompoundTypeInformationProviderRegistry(worldKey);
    }

    /**
     * Registers an information provider used during analysis directly.
     *
     * @param provider The provider that is being registered.
     * @return The registry with the provider added.
     */
    IValidCompoundTypeInformationProviderRegistry registerNewProvider(@NotNull final IValidCompoundTypeInformationProvider<?> provider);

    /**
     * Registers an information provider used during analysis.
     * This information provider is build from the given class and callback.
     *
     * @param clazz The class of the game object for which the callback serves as a {@link IValidCompoundTypeInformationProvider}
     * @param decider The callback that determines if a given compound type is valid for a given game object.
     * @param <T> The type of the game object.
     * @return The registry with the provider, constructed from the class and callback, added.
     */
    default <T> IValidCompoundTypeInformationProviderRegistry registerNewProvider(@NotNull final Class<T> clazz, @NotNull final BiFunction<ICompoundContainer<T>, ICompoundType, Optional<Boolean>> decider)
    {
        return this.registerNewProvider(new SimpleBiFunctionBasedValidCompoundTypeInformationProvider<>(clazz, decider));
    }
}
