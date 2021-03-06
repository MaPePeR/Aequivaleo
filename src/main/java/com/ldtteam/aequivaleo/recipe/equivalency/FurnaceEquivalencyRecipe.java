package com.ldtteam.aequivaleo.recipe.equivalency;

import com.ldtteam.aequivaleo.api.compound.container.ICompoundContainer;
import com.ldtteam.aequivaleo.api.recipe.equivalency.IFurnaceEquivalencyRecipe;
import net.minecraft.util.ResourceLocation;

import java.util.Set;
import java.util.stream.Collectors;

public class FurnaceEquivalencyRecipe implements IFurnaceEquivalencyRecipe
{
    private final ResourceLocation recipeName;
    private final Set<ICompoundContainer<?>> inputs;
    private final Set<ICompoundContainer<?>> outputs;

    public FurnaceEquivalencyRecipe(
      final ResourceLocation recipeName, final Set<ICompoundContainer<?>> inputs,
      final Set<ICompoundContainer<?>> outputs) {
        this.recipeName = recipeName;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    /**
     * The compound containers that are the input for this recipe.
     *
     * @return The inputs.
     */
    @Override
    public Set<ICompoundContainer<?>> getInputs()
    {
        return inputs;
    }

    /**
     * The compound containers that are the output for this recipe.
     *
     * @return The output.
     */
    @Override
    public Set<ICompoundContainer<?>> getOutputs()
    {
        return outputs;
    }

    /**
     * Returns the offset factor between inputs and outputs.
     */
    @Override
    public Double getOffsetFactor()
    {
        return 1D;
    }

    public ResourceLocation getRecipeName()
    {
        return recipeName;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof FurnaceEquivalencyRecipe))
        {
            return false;
        }

        final FurnaceEquivalencyRecipe that = (FurnaceEquivalencyRecipe) o;

        if (getInputs() != null ? !getInputs().equals(that.getInputs()) : that.getInputs() != null)
        {
            return false;
        }
        return getOutputs() != null ? getOutputs().equals(that.getOutputs()) : that.getOutputs() == null;
    }

    @Override
    public int hashCode()
    {
        int result = getInputs() != null ? getInputs().hashCode() : 0;
        result = 31 * result + (getOutputs() != null ? getOutputs().hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return String.format("Equivalent via smelting: %s", recipeName);
    }
}
