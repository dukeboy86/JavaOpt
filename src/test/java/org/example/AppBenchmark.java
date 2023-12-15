package org.example;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Measurement(iterations = 6, time = 10, timeUnit = TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 1)
@State(Scope.Benchmark)
public class AppBenchmark {

    @State(Scope.Thread)
    public static class StateClassic {

        public RowContainer container;

        public StateClassic() {
            container = new App(PersistanceOption.CLASSIC_COLLECTION).loadData();
        }
    }

    @State(Scope.Thread)
    public static class StateOptimized {

        public RowContainer container;

        public StateOptimized() {
            container = new App(PersistanceOption.OPTIMIZED_COLLECTION).loadData();
        }
    }

    @State(Scope.Thread)
    public static class StateDatabaseDisk {

        public RowContainer container;

        public StateDatabaseDisk() {
            container = new App(PersistanceOption.DATABASE_DISK).loadData();
        }
    }

    @State(Scope.Thread)
    public static class StateDatabaseMemory {

        public RowContainer container;

        public StateDatabaseMemory() {
            container = new App(PersistanceOption.DATABASE_MEMORY).loadData();
        }
    }

    // Laden messen

    @Benchmark
    public void benchmarkLoadClassic(Blackhole hole) {
        hole.consume(new App(PersistanceOption.CLASSIC_COLLECTION).loadData());
    }

    @Benchmark
    public void benchmarkLoadOptimized(Blackhole hole) {
        hole.consume(new App(PersistanceOption.OPTIMIZED_COLLECTION).loadData());
    }

    @Benchmark
    public void benchmarkLoadDatabaseDisk(Blackhole hole) {
        hole.consume(new App(PersistanceOption.DATABASE_DISK).loadData());
    }

    @Benchmark
    public void benchmarkLoadDatabaseMemory(Blackhole hole) {
        hole.consume(new App(PersistanceOption.DATABASE_MEMORY).loadData());
    }

    // Suchen nach ID messen

    public static final long[] IDS = {42, 69, 420, 735, 10_000};

    @Benchmark
    public void benchmarkLookupByIdClassic(Blackhole hole, StateClassic state) {
        for (long id : IDS) {
            hole.consume(state.container.getById(id));
        }
    }

    @Benchmark
    public void benchmarkLookupByIdOptimized(Blackhole hole, StateOptimized state) {
        for (long id : IDS) {
            hole.consume(state.container.getById(id));
        }
    }

    @Benchmark
    public void benchmarkLookupByIdDatabaseDisk(Blackhole hole,StateDatabaseDisk  state) {
        for (long id : IDS) {
            hole.consume(state.container.getById(id));
        }
    }

    @Benchmark
    public void benchmarkLookupByIdDatabaseMemory(Blackhole hole, StateDatabaseMemory state) {
        for (long id : IDS) {
            hole.consume(state.container.getById(id));
        }
    }

    // Suchen nach Topic messen

    public static final long[] TOPICS = {7, 8, 9, 420, 3};

    @Benchmark
    public void benchmarkLookupByTopicClassic(Blackhole hole, StateClassic state) {
        for (long topic : TOPICS) {
            hole.consume(state.container.getByTopic(topic));
        }
    }

    @Benchmark
    public void benchmarkLookupByTopicOptimized(Blackhole hole, StateOptimized state) {
        for (long topic : TOPICS) {
            hole.consume(state.container.getByTopic(topic));
        }
    }

    @Benchmark
    public void benchmarkLookupByTopicDatabaseDisk(Blackhole hole, StateDatabaseDisk state) {
        for (long topic : TOPICS) {
            hole.consume(state.container.getByTopic(topic));
        }
    }

    @Benchmark
    public void benchmarkLookupByTopicDatabaseMemory(Blackhole hole, StateDatabaseMemory state) {
        for (long topic : TOPICS) {
            hole.consume(state.container.getByTopic(topic));
        }
    }

    // Suchen nach Name messen

    public static final List<String> TITLES = List.of(
            "what is java(TM)?",
            "why do muslims woman wear long dresses?",
            "How do i quit being so suspicious of my boyfriend who is nothing but devoted to me?",
            "how to create hair textures with gimp?",
            "what is eyoon?",
            "Dieser Title existiert nicht");

    @Benchmark
    public void benchmarkLookupByTitleClassic(Blackhole hole, StateClassic state) {
        for (String title : TITLES) {
            hole.consume(state.container.getByTitle(title));
        }
    }

    @Benchmark
    public void benchmarkLookupByTitleOptimized(Blackhole hole, StateOptimized state) {
        for (String title : TITLES) {
            hole.consume(state.container.getByTitle(title));
        }
    }

    @Benchmark
    public void benchmarkLookupByTitleDatabaseDisk(Blackhole hole, StateDatabaseDisk state) {
        for (String title : TITLES) {
            hole.consume(state.container.getByTitle(title));
        }
    }

    @Benchmark
    public void benchmarkLookupByTitleDatabaseMemory(Blackhole hole, StateDatabaseMemory state) {
        for (String title : TITLES) {
            hole.consume(state.container.getByTitle(title));
        }
    }

    // Nach einem vorhandenen Wort suchen & messen

//    public static final List<String> KEYWORDS = List.of("java", "marijuana");
//
//    @Benchmark
//    public void benchmarkSearchByKeywordClassic(Blackhole hole, StateClassic state) {
//        for (String keyword : KEYWORDS) {
//            hole.consume(state.container.findIdsByKeyword(keyword));
//        }
//    }
//
//    @Benchmark
//    public void benchmarkSearchByKeywordOptimized(Blackhole hole, StateOptimized state) {
//        for (String keyword : KEYWORDS) {
//            hole.consume(state.container.findIdsByKeyword(keyword));
//        }
//    }
//
//    @Benchmark
//    public void benchmarkSearchByKeywordDatabaseDisk(Blackhole hole, StateDatabaseDisk state) {
//        for (String keyword : KEYWORDS) {
//            hole.consume(state.container.findIdsByKeyword(keyword));
//        }
//    }
//
//    @Benchmark
//    public void benchmarkSearchByKeywordDatabaseMemory(Blackhole hole, StateDatabaseMemory state) {
//        for (String keyword : KEYWORDS) {
//            hole.consume(state.container.findIdsByKeyword(keyword));
//        }
//    }
}
