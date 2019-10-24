package org.verzhbitski.weekviewsample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewDisplayable;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    WeekView<DayViewEvent> weekView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weekView = findViewById(R.id.calendar_day_view);

        weekView.setOnEventClickListener((dayViewEvent, rectF) -> {
            Toast.makeText(this, dayViewEvent.title, Toast.LENGTH_LONG).show();
        });

        ArrayList<DayViewEvent> dayViewEvents = new ArrayList<>();
        dayViewEvents.add(new DayViewEvent());

        weekView.submit(dayViewEvents);
    }

    static class DayViewEvent implements WeekViewDisplayable<DayViewEvent> {

        private long id;
        private String title;
        private String location = "";
        private Calendar start;
        private Calendar end;
        private boolean allDay;

        public String taskUuid;

        public DayViewEvent() {
            id = "Sample".hashCode();
            title = "Sample";
            start = Calendar.getInstance();
            Calendar endC = Calendar.getInstance();
            Date now = new Date();
            endC.setTime(new Date(now.getTime() + 24 * 60 * 60 * 1000));
            end = endC;
            allDay = true;
        }

        @Override
        public WeekViewEvent<DayViewEvent> toWeekViewEvent() {

            return new WeekViewEvent.Builder<>(this)
                    .setId(id)
                    .setTitle(title)
                    .setLocation(location)
                    .setStartTime(start)
                    .setEndTime(end)
                    .setAllDay(allDay)
                    .build();
        }
    }
}
