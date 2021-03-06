/*
Copyright 2020 Weebkun

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.weebkun.util.models;

import net.dv8tion.jda.api.entities.Member;

/**
 * Class that represents a vote.
 * Stores the Member instance of the voter and the choice he/she made.
 * @author weebkun
 */
public class Vote {

    /**
     * Member instance of voter.
     */
    public Member voter;
    /**
     * choice of voter.
     */
    public String choice;

    public Vote(Member voter, String choice){
        this.voter = voter;
        this.choice = choice;
    }
}
