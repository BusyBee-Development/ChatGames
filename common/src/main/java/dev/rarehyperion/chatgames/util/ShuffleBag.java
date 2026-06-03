package dev.rarehyperion.chatgames.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class ShuffleBag<T> {

    private final List<T> source;
    private final List<T> remaining;

    public ShuffleBag(final List<T> source) {
        if(source == null || source.isEmpty()) {
            throw new IllegalArgumentException("ShuffleBag source cannot be empty.");
        }

        this.source = Collections.unmodifiableList(new ArrayList<>(source));
        this.remaining = new ArrayList<>(this.source);
        Collections.shuffle(this.remaining, ThreadLocalRandom.current());
    }

    public synchronized T next() {
        if(this.remaining.isEmpty()) {
            this.remaining.addAll(this.source);
            Collections.shuffle(this.remaining, ThreadLocalRandom.current());
        }

        return this.remaining.remove(this.remaining.size() - 1);
    }

}
