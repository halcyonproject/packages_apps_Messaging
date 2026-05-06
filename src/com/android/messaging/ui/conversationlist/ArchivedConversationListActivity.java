/*
 * Copyright (C) 2015 The Android Open Source Project
 * Copyright (C) 2024-2025 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.messaging.ui.conversationlist;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import com.android.messaging.R;

public class ArchivedConversationListActivity extends AbstractConversationListActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation_list_activity);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mConversationListFragment =
                ConversationListFragment.createArchivedConversationListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, mConversationListFragment)
                .commit();
        invalidateActionBar();
    }

    @Override
    protected void updateActionBar(ActionBar actionBar) {
        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar_layout);
        if (collapsingToolbar != null) {
            collapsingToolbar.setTitle(getString(R.string.archived_activity_title));
        } else {
            actionBar.setTitle(getString(R.string.archived_activity_title));
        }
        actionBar.setDisplayShowTitleEnabled(collapsingToolbar == null);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
        super.updateActionBar(actionBar);
    }

    @Override
    public void onBackPressed() {
        if (isInConversationListSelectMode()) {
            exitMultiSelectState();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onActionBarHome();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onActionBarHome() {
        onBackPressed();
    }

    @Override
    public boolean isSwipeAnimatable() {
        return false;
    }
}
