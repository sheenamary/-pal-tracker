package io.pivotal.pal.tracker;

import java.util.*;


public class InMemoryTimeEntryRepository implements TimeEntryRepository {


    private Map<Long, TimeEntry> timeEntryDataSet = new HashMap<Long, TimeEntry>();
    private long entryId = 1L;
    public TimeEntry create(TimeEntry timeEntry){
        TimeEntry createdTimeEntry = new TimeEntry(entryId,timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours());
        timeEntryDataSet.put(entryId, createdTimeEntry);
        TimeEntry getTimeEntry = timeEntryDataSet.get(entryId);
        entryId++;
        return getTimeEntry;
          }

    @Override
    public TimeEntry find(long id) {
        TimeEntry fetchResult=timeEntryDataSet.get(id);
        return fetchResult;
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList(timeEntryDataSet.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (!timeEntryDataSet.containsKey(id)) {
            return null;
        }
        TimeEntry timeEntryOld = timeEntryDataSet.get(id);
        timeEntry.setId(timeEntryOld.getId());
        timeEntryDataSet.put(id,timeEntry);
        return timeEntryDataSet.get(id);
    }

    @Override
    public void delete(long id) {
        TimeEntry entryTobeDeleted = timeEntryDataSet.get(id);
        timeEntryDataSet.remove(id,entryTobeDeleted);
    }
}
