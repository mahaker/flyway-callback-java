package db.migration;

import java.sql.PreparedStatement;

import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;

public class FillTestData implements Callback {

    @Override
    public boolean supports(Event event, Context context) {
        return Event.AFTER_MIGRATE.equals(event);
    }

    @Override
    public boolean canHandleInTransaction(Event event, Context context) {
        return false;
    }

    @Override
    public void handle(Event event, Context context) {
        try (
            final PreparedStatement statement = context
                .getConnection()
                .prepareStatement("INSERT INTO PERSON(ID, NAME, AGE) VALUES (1, 'userA', 20), (2, 'userB', 22)")
        ) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
