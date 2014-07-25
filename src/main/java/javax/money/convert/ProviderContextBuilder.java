package javax.money.convert;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.money.AbstractContextBuilder;

/**
 * Builder class to create {@link ProviderContext} instances. Instances of
 * this class are not thread-safe.
 *
 * @author Anatole Tresch
 */
public final class ProviderContextBuilder extends AbstractContextBuilder<ProviderContextBuilder, ProviderContext>{

    /**
     * Create a new Builder instance.
     *
     * @param provider the provider name, not {@code null}.
     * @param rateTypes the rate types, not null and not empty.
     */
    public ProviderContextBuilder(String provider, RateType rateType, RateType... rateTypes){
        Objects.requireNonNull(rateType, "At least one RateType is required.");
        Objects.requireNonNull(rateTypes);
        setProviderName(provider);
        Set<RateType> rts = new HashSet<>();
        rts.add(rateType);
        Collections.addAll(rts, rateTypes);
        setSet(ProviderContext.RATE_TYPES, rts);
    }

    /**
     * Create a new Builder instance.
     *
     * @param provider  the provider name, not {@code null}.
     * @param rateTypes the rate types, not null and not empty.
     */
    public ProviderContextBuilder(String provider, Collection<RateType> rateTypes){
        Objects.requireNonNull(rateTypes);
        if(rateTypes.isEmpty()){
            throw new IllegalArgumentException("At least one RateType is required.");
        }
        setProviderName(provider);
        Set<RateType> rts = new HashSet<>();
        rts.addAll(rateTypes);
        setSet("rateTypes", rts);
    }

    /**
     * Create a new Builder, hereby using the given {@link ProviderContext}
     * 's values as defaults. This allows changing an existing
     * {@link ProviderContext} easily.
     *
     * @param context the context, not {@code null}
     */
    public ProviderContextBuilder(ProviderContext context){
        importContext(context);
        Set<RateType> rts = new HashSet<>();
        rts.addAll(context.getRateTypes());
        setSet("rateTypes", rts);
    }

    /**
     * Sets the provider name.
     *
     * @param provider the new provider name
     * @return this, for chaining.
     */
    public ProviderContextBuilder setProviderName(String provider){
        Objects.requireNonNull(provider);
        set(ProviderContext.PROVIDER, provider);
        return this;
    }

    /**
     * Set the rate types.
     *
     * @param rateTypes the rate types, not null and not empty.
     * @return this, for chaining.
     * @throws IllegalArgumentException when not at least one {@link RateType} is provided.
     */
    public ProviderContextBuilder setRateTypes(RateType... rateTypes){
        return setRateTypes(Arrays.asList(rateTypes));
    }

    /**
     * Set the rate types.
     *
     * @param rateTypes the rate types, not null and not empty.
     * @return this, for chaining.
     * @throws IllegalArgumentException when not at least one {@link RateType} is provided.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ProviderContextBuilder setRateTypes(Collection<RateType> rateTypes){
        Objects.requireNonNull(rateTypes);
        if(rateTypes.size() == 0){
            throw new IllegalArgumentException("At least one RateType is required.");
        }
        Set rtSet = new HashSet<>();
        rtSet.addAll(rateTypes);
        setSet(ProviderContext.RATE_TYPES, rtSet);
        return this;
    }

    /**
     * Creates a new {@link ProviderContext} with the data from this Builder
     * instance.
     *
     * @return a new {@link ProviderContext}. never {@code null}.
     */
    public ProviderContext build(){
        return new ProviderContext(this);
    }


}