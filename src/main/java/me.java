/*
 * Copyright (C) 2010-2014 Mojang AB
 *
 * Parts of this program are marked with "VanillaIRC". These will be referred
 * to as "Modification" from this point on.
 *
 * Modification Copyright (C) 2014 MinecraftOnline
 *
 * The Modification is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Modification is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.minecraftonline.vanillairc.ChatHandler; // VanillaIRC
import com.minecraftonline.vanillairc.VanillaIRC; // VanillaIRC
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class me {

    public static final File a = new File("banned-players.json");
    public static final File b = new File("banned-ips.json");
    public static final File c = new File("ops.json");
    public static final File d = new File("whitelist.json");
    private static final Logger f = LogManager.getLogger();
    private static final SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
    private final MinecraftServer h;
    private final List<lm> i = Lists.newArrayList();
    private final Map<UUID, lm> j = Maps.newHashMap();
    private final mj k;
    private final mb l;
    private final mf m;
    private final ml n;
    private final Map<UUID, nc> o;
    private ayb p;
    private boolean q;
    protected int e;
    private int r;
    private agq.a s; // VanillaIRC -- faulty decompilation: agq -> agq.a
    private boolean t;
    private int u;

    public me(MinecraftServer var1) {
        this.k = new mj(a);
        this.l = new mb(b);
        this.m = new mf(c);
        this.n = new ml(d);
        this.o = Maps.newHashMap();
        this.h = var1;
        this.k.a(false);
        this.l.a(false);
        this.e = 8;
    }

    public void a(ek var1, lm var2) {
        GameProfile var3 = var2.cm();
        ma var4 = this.h.aH();
        GameProfile var5 = var4.a(var3.getId());
        String var6 = var5 == null ? var3.getName() : var5.getName();

        var4.a(var3);
        dn var7 = this.a(var2);

        var2.a((agn) this.h.a(var2.al));
        var2.c.a((lk) var2.n);
        String var8 = "local";

        if (var1.b() != null) {
            var8 = var1.b().toString();
        }

        f.info(var2.e_() + "[" + var8 + "] logged in with entity id " + var2.G() + " at (" + var2.r + ", " + var2.s + ", " + var2.t + ")");
        lk var9 = this.h.a(var2.al);
        axs var10 = var9.R();
        cj var11 = var9.O();

        this.a(var2, (lm) null, var9);
        lt var12 = new lt(this.h, var1, var2);

        var12.a((ff) (new gu(var2.G(), var2.c.b(), var10.s(), var9.t.p().a(), var9.ac(), this.p(), var10.t(), var9.S().b("reducedDebugInfo"))));
        var12.a((ff) (new gi("MC|Brand", (new em(Unpooled.buffer())).a(this.c().getServerModName()))));
        var12.a((ff) (new fx(var10.x(), var10.y())));
        var12.a((ff) (new hu(var11)));
        var12.a((ff) (new gy(var2.bH)));
        var12.a((ff) (new hj(var2.bp.d)));
        int var13 = this.h(var3) ? this.m.a(var3) : 0;

        var13 = this.h.V() && this.h.e[0].R().u() ? 4 : var13;
        var13 = this.t ? 4 : var13;
        this.a(var2, var13);
        var2.B().d();
        var2.B().b(var2);
        this.a((kq) var9.ab(), var2);
        this.h.aJ();
        fb var14;

        if (!var2.e_().equalsIgnoreCase(var6)) {
            var14 = new fb("multiplayer.player.joined.renamed", new Object[] { var2.f_(), var6});
        } else {
            var14 = new fb("multiplayer.player.joined", new Object[] { var2.f_()});
        }

        // VanillaIRC start - field "a" blocks class "a"
        try {
            var14.b().a((a) Class.forName("a").getDeclaredField("o").get(null));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        } // VanillaIRC end
        this.a((eu) var14);
        this.c(var2);
        var12.a(var2.r, var2.s, var2.t, var2.x, var2.y);
        this.b(var2, var9);
        if (!this.h.ad().isEmpty()) {
            var2.a(this.h.ad(), this.h.ae());
        }

        Iterator var15 = var2.bt().iterator();

        while (var15.hasNext()) {
            qs var16 = (qs) var15.next();

            var12.a((ff) (new ic(var2.G(), var16)));
        }

        var2.g_();
        if (var7 != null && var7.b("Riding", 10)) {
            qy var17 = ra.a(var7.o("Riding"), (agn) var9);

            if (var17 != null) {
                var17.m = true;
                var9.a(var17);
                var2.a(var17);
                var17.m = false;
            }
        }

    }

    protected void a(kq var1, lm var2) {
        HashSet var3 = Sets.newHashSet();
        Iterator var4 = var1.g().iterator();

        while (var4.hasNext()) {
            ayo var5 = (ayo) var4.next();

            var2.a.a((ff) (new hs(var5, 0)));
        }

        for (int var9 = 0; var9 < 19; ++var9) {
            ayn var10 = var1.a(var9);

            if (var10 != null && !var3.contains(var10)) {
                List var6 = var1.d(var10);
                Iterator var7 = var6.iterator();

                while (var7.hasNext()) {
                    ff var8 = (ff) var7.next();

                    var2.a.a(var8);
                }

                var3.add(var10);
            }
        }

    }

    public void a(lk[] var1) {
        this.p = var1[0].Q().e();
        var1[0].ah().a(new aqd() {
            public void a(aqf var1, double var2) {
                me.this.a((ff) (new hh(var1, hh.a.a))); // VanillaIRC -- faulty decompilation: hh -> hh.a
            }

            public void a(aqf var1, double var2, double var4, long var6) {
                me.this.a((ff) (new hh(var1, hh.a.b))); // VanillaIRC -- faulty decompilation: hh -> hh.a
            }

            public void a(aqf var1, double var2, double var4) {
                me.this.a((ff) (new hh(var1, hh.a.c))); // VanillaIRC -- faulty decompilation: hh -> hh.a
            }

            public void a(aqf var1, int var2) {
                me.this.a((ff) (new hh(var1, hh.a.e))); // VanillaIRC -- faulty decompilation: hh -> hh.a
            }

            public void b(aqf var1, int var2) {
                me.this.a((ff) (new hh(var1, hh.a.f))); // VanillaIRC -- faulty decompilation: hh -> hh.a
            }

            public void b(aqf var1, double var2) {}

            public void c(aqf var1, double var2) {}
        });
    }

    public void a(lm var1, lk var2) {
        lk var3 = var1.v();

        if (var2 != null) {
            var2.v().c(var1);
        }

        var3.v().a(var1);
        var3.b.c((int) var1.r >> 4, (int) var1.t >> 4);
    }

    public int d() {
        return lh.b(this.s());
    }

    public dn a(lm var1) {
        dn var2 = this.h.e[0].R().h();
        dn var3;

        if (var1.e_().equals(this.h.U()) && var2 != null) {
            var3 = this.h.aP().a((om) on.b, var2);
            var1.f(var3);
            f.debug("loading single player");
        } else {
            var3 = this.p.b(var1);
        }

        return var3;
    }

    protected void b(lm var1) {
        this.p.a(var1);
        nc var2 = (nc) this.o.get(var1.aQ());

        if (var2 != null) {
            var2.b();
        }

    }

    public void c(lm var1) {
        this.i.add(var1);
        this.j.put(var1.aQ(), var1);
        this.a((ff) (new ha(ha.a.a, new lm[] { var1}))); // VanillaIRC -- faulty decompilation: ha -> ha.a
        lk var2 = this.h.a(var1.al);

        var2.a((qy) var1);
        this.a(var1, (lk) null);

        for (int var3 = 0; var3 < this.i.size(); ++var3) {
            lm var4 = (lm) this.i.get(var3);

            var1.a.a((ff) (new ha(ha.a.a, new lm[] { var4}))); // VanillaIRC -- faulty decompilation: ha -> ha.a
        }

    }

    public void d(lm var1) {
        var1.v().v().d(var1);
    }

    public void e(lm var1) {
        var1.b(nh.f);
        this.b(var1);
        lk var2 = var1.v();

        if (var1.l != null) {
            var2.f(var1.l);
            f.debug("removing player mount");
        }

        var2.e(var1);
        var2.v().c(var1);
        this.i.remove(var1);
        UUID var3 = var1.aQ();
        lm var4 = (lm) this.j.get(var3);

        if (var4 == var1) {
            this.j.remove(var3);
            this.o.remove(var3);
        }

        this.a((ff) (new ha(ha.a.e, new lm[] { var1}))); // VanillaIRC -- faulty decompilation: ha -> ha.a
    }

    public String a(SocketAddress var1, GameProfile var2) {
        String var4;

        if (this.k.a(var2)) {
            mk var5 = (mk) ((mi) this.k).b(var2); // VanillaIRC -- obfuscation anomaly: cast this.k to mi

            var4 = "You are banned from this server!\nReason: " + var5.d();
            if (var5.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + g.format(var5.c());
            }

            return var4;
        } else if (!this.e(var2)) {
            return "You are not white-listed on this server!";
        } else if (this.l.a(var1)) {
            mc var3 = this.l.b(var1);

            var4 = "Your IP address is banned from this server!\nReason: " + var3.d();
            if (var3.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + g.format(var3.c());
            }

            return var4;
        } else {
            return this.i.size() >= this.e && !this.f(var2) ? "The server is full!" : null;
        }
    }

    public lm g(GameProfile var1) {
        UUID var2 = yt.a(var1);
        ArrayList var3 = Lists.newArrayList();

        for (int var4 = 0; var4 < this.i.size(); ++var4) {
            lm var5 = (lm) this.i.get(var4);

            if (var5.aQ().equals(var2)) {
                var3.add(var5);
            }
        }

        lm var7 = (lm) this.j.get(var1.getId());

        if (var7 != null && !var3.contains(var7)) {
            var3.add(var7);
        }

        Iterator var8 = var3.iterator();

        while (var8.hasNext()) {
            lm var6 = (lm) var8.next();

            var6.a.c("You logged in from another location");
        }

        Object var9;

        if (this.h.Z()) {
            var9 = new le(this.h.a(0));
        } else {
            var9 = new ln(this.h.a(0));
        }

        return new lm(this.h, this.h.a(0), var1, (ln) var9);
    }

    public lm a(lm var1, int var2, boolean var3) {
        var1.v().u().b(var1);
        var1.v().u().b((qy) var1);
        var1.v().v().c(var1);
        this.i.remove(var1);
        this.h.a(var1.al).f(var1);
        cj var4 = var1.cq();
        boolean var5 = var1.cr();

        var1.al = var2;
        Object var6;

        if (this.h.Z()) {
            var6 = new le(this.h.a(var1.al));
        } else {
            var6 = new ln(this.h.a(var1.al));
        }

        lm var7 = new lm(this.h, this.h.a(var1.al), var1.cm(), (ln) var6);

        var7.a = var1.a;
        var7.a((yt) var1, var3);
        var7.d(var1.G());
        var7.o(var1);
        var7.a(var1.bX());
        Iterator var8 = var1.H().iterator();

        while (var8.hasNext()) {
            String var9 = (String) var8.next();

            var7.a(var9);
        }

        lk var10 = this.h.a(var1.al);

        this.a(var7, var1, var10);
        cj var11;

        if (var4 != null) {
            var11 = yt.a(this.h.a(var1.al), var4, var5);
            if (var11 != null) {
                var7.b((double) ((float) var11.n() + 0.5F), (double) ((float) var11.o() + 0.1F), (double) ((float) var11.p() + 0.5F), 0.0F, 0.0F);
                var7.a(var4, var5);
            } else {
                var7.a.a((ff) (new go(0, 0.0F)));
            }
        }

        var10.b.c((int) var7.r >> 4, (int) var7.t >> 4);

        while (!var10.a((qy) var7, var7.aY()).isEmpty() && var7.s < 256.0D) {
            var7.b(var7.r, var7.s + 1.0D, var7.t);
        }

        var7.a.a((ff) (new hf(var7.al, var7.n.ac(), var7.n.R().t(), var7.c.b())));
        var11 = var10.O();
        var7.a.a(var7.r, var7.s, var7.t, var7.x, var7.y);
        var7.a.a((ff) (new hu(var11)));
        var7.a.a((ff) (new hp(var7.bK, var7.bJ, var7.bI)));
        this.b(var7, var10);
        var10.v().a(var7);
        var10.a((qy) var7);
        this.i.add(var7);
        this.j.put(var7.aQ(), var7);
        var7.g_();
        var7.c(var7.bv());
        return var7;
    }

    public void a(lm var1, int var2, cj var3) {
        int var4 = var1.al;
        lk var5 = this.h.a(var1.al);

        var1.al = var2;
        lk var6 = this.h.a(var1.al);

        var1.a.a((ff) (new hf(var1.al, var1.n.ac(), var1.n.R().t(), var1.c.b())));
        var5.f(var1);
        var1.H = false;
        if (var3 == null) {
            this.a(var1, var4, var5, var6);
        } else if (var1.al()) {
            var5.a((qy) var1, false);
            var1.a(var3, var1.x, var1.y);
            var6.a((qy) var1);
            var6.a((qy) var1, false);
            var1.a((agn) var6);
        }

        this.a(var1, var5);
        var1.a.a(var1.r, var1.s, var1.t, var1.x, var1.y);
        var1.c.a(var6);
        this.b(var1, var6);
        this.f(var1);
        Iterator var7 = var1.bt().iterator();

        while (var7.hasNext()) {
            qs var8 = (qs) var7.next();

            var1.a.a((ff) (new ic(var1.G(), var8)));
        }

    }

    public void a(qy var1, int var2, lk var3, lk var4) {
        double var5 = var1.r;
        double var7 = var1.t;
        double var9 = 8.0D;
        float var11 = var1.x;

        var3.B.a("moving");
        if (var1.al == -1) {
            var5 = ob.a(var5 / var9, var4.ah().b() + 16.0D, var4.ah().d() - 16.0D);
            var7 = ob.a(var7 / var9, var4.ah().c() + 16.0D, var4.ah().e() - 16.0D);
            var1.b(var5, var1.s, var7, var1.x, var1.y);
            if (var1.al()) {
                var3.a(var1, false);
            }
        } else if (var1.al == 0) {
            var5 = ob.a(var5 * var9, var4.ah().b() + 16.0D, var4.ah().d() - 16.0D);
            var7 = ob.a(var7 * var9, var4.ah().c() + 16.0D, var4.ah().e() - 16.0D);
            var1.b(var5, var1.s, var7, var1.x, var1.y);
            if (var1.al()) {
                var3.a(var1, false);
            }
        } else {
            cj var12;

            if (var2 == 1) {
                var12 = var4.O();
            } else {
                var12 = var4.o();
            }

            var5 = (double) var12.n();
            var1.s = (double) var12.o();
            var7 = (double) var12.p();
            var1.b(var5, var1.s, var7, 90.0F, 0.0F);
            if (var1.al()) {
                var3.a(var1, false);
            }
        }

        var3.B.b();
        if (var2 != 1) {
            var3.B.a("placing");
            var5 = (double) ob.a((int) var5, -29999872, 29999872);
            var7 = (double) ob.a((int) var7, -29999872, 29999872);
            if (var1.al()) {
                var1.b(var5, var1.s, var7, var1.x, var1.y);
                var4.w().a(var1, var11);
                var4.a(var1);
                var4.a(var1, false);
            }

            var3.B.b();
        }

        var1.a((agn) var4);
    }

    public void e() {
        if (++this.u > 600) {
            this.a((ff) (new ha(ha.a.c, this.i))); // VanillaIRC -- faulty decompilation: ha -> ha.a
            this.u = 0;
        }

    }

    public void a(ff var1) {
        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            ((lm) this.i.get(var2)).a.a(var1);
        }

    }

    public void a(ff var1, int var2) {
        for (int var3 = 0; var3 < this.i.size(); ++var3) {
            lm var4 = (lm) this.i.get(var3);

            if (var4.al == var2) {
                var4.a.a(var1);
            }
        }

    }

    public void a(yt var1, eu var2) {
        ayt var3 = var1.aE();

        if (var3 != null) {
            Collection var4 = var3.d();
            Iterator var5 = var4.iterator();

            while (var5.hasNext()) {
                String var6 = (String) var5.next();
                lm var7 = this.a(var6);

                if (var7 != null && var7 != var1) {
                    var7.a(var2);
                }
            }

        }
    }

    public void b(yt var1, eu var2) {
        ayt var3 = var1.aE();

        if (var3 == null) {
            this.a(var2);
        } else {
            for (int var4 = 0; var4 < this.i.size(); ++var4) {
                lm var5 = (lm) this.i.get(var4);

                if (var5.aE() != var3) {
                    var5.a(var2);
                }
            }

        }
    }

    public String b(boolean var1) {
        String var2 = "";
        ArrayList var3 = Lists.newArrayList(this.i);

        for (int var4 = 0; var4 < var3.size(); ++var4) {
            if (var4 > 0) {
                var2 = var2 + ", ";
            }

            var2 = var2 + ((lm) var3.get(var4)).e_();
            if (var1) {
                var2 = var2 + " (" + ((lm) var3.get(var4)).aQ().toString() + ")";
            }
        }

        return var2;
    }

    public String[] f() {
        String[] var1 = new String[this.i.size()];

        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            var1[var2] = ((lm) this.i.get(var2)).e_();
        }

        return var1;
    }

    public GameProfile[] g() {
        GameProfile[] var1 = new GameProfile[this.i.size()];

        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            var1[var2] = ((lm) this.i.get(var2)).cm();
        }

        return var1;
    }

    public mj h() {
        return this.k;
    }

    public mb i() {
        return this.l;
    }

    public void a(GameProfile var1) {
        int var2 = this.h.p();

        ((mi) this.m).a((mh) (new mg(var1, this.h.p(), this.m.b(var1)))); // VanillaIRC -- obfuscation anomaly: cast this.m to mi
        this.a(this.a(var1.getId()), var2);
    }

    public void b(GameProfile var1) {
        this.m.c(var1);
        this.a(this.a(var1.getId()), 0);
    }

    private void a(lm var1, int var2) {
        if (var1 != null && var1.a != null) {
            byte var3;

            if (var2 <= 0) {
                var3 = 24;
            } else if (var2 >= 4) {
                var3 = 28;
            } else {
                var3 = (byte) (24 + var2);
            }

            var1.a.a((ff) (new gk(var1, var3)));
        }

    }

    public boolean e(GameProfile var1) {
        return !this.q || this.m.d(var1) || this.n.d(var1);
    }

    public boolean h(GameProfile var1) {
        return this.m.d(var1) || this.h.V() && this.h.e[0].R().u() && this.h.U().equalsIgnoreCase(var1.getName()) || this.t;
    }

    public lm a(String var1) {
        Iterator var2 = this.i.iterator();

        lm var3;

        do {
            if (!var2.hasNext()) {
                return null;
            }

            var3 = (lm) var2.next();
        } while (!var3.e_().equalsIgnoreCase(var1));

        return var3;
    }

    public void a(double var1, double var3, double var5, double var7, int var9, ff var10) {
        this.a((yt) null, var1, var3, var5, var7, var9, var10);
    }

    public void a(yt var1, double var2, double var4, double var6, double var8, int var10, ff var11) {
        for (int var12 = 0; var12 < this.i.size(); ++var12) {
            lm var13 = (lm) this.i.get(var12);

            if (var13 != var1 && var13.al == var10) {
                double var14 = var2 - var13.r;
                double var16 = var4 - var13.s;
                double var18 = var6 - var13.t;

                if (var14 * var14 + var16 * var16 + var18 * var18 < var8 * var8) {
                    var13.a.a(var11);
                }
            }
        }

    }

    public void j() {
        for (int var1 = 0; var1 < this.i.size(); ++var1) {
            this.b((lm) this.i.get(var1));
        }

    }

    public void d(GameProfile var1) {
        ((mi) this.n).a((mh) (new mm(var1))); // VanillaIRC -- obfuscation anomaly: cast this.m to mi
    }

    public void c(GameProfile var1) {
        this.n.c(var1);
    }

    public ml k() {
        return this.n;
    }

    public String[] l() {
        return this.n.a();
    }

    public mf m() {
        return this.m;
    }

    public String[] n() {
        return this.m.a();
    }

    public void a() {}

    public void b(lm var1, lk var2) {
        aqf var3 = this.h.e[0].ah();

        var1.a.a((ff) (new hh(var3, hh.a.d))); // VanillaIRC -- faulty decompilation: hh -> hh.a
        var1.a.a((ff) (new hv(var2.M(), var2.N(), var2.S().b("doDaylightCycle"))));
        if (var2.U()) {
            var1.a.a((ff) (new go(1, 0.0F)));
            var1.a.a((ff) (new go(7, var2.j(1.0F))));
            var1.a.a((ff) (new go(8, var2.h(1.0F))));
        }

    }

    public void f(lm var1) {
        var1.a(var1.bq);
        var1.s();
        var1.a.a((ff) (new hj(var1.bp.d)));
    }

    public int o() {
        return this.i.size();
    }

    public int p() {
        return this.e;
    }

    public String[] q() {
        return this.h.e[0].Q().e().f();
    }

    public boolean r() {
        return this.q;
    }

    public void a(boolean var1) {
        this.q = var1;
    }

    public List<lm> b(String var1) {
        ArrayList var2 = Lists.newArrayList();
        Iterator var3 = this.i.iterator();

        while (var3.hasNext()) {
            lm var4 = (lm) var3.next();

            if (var4.x().equals(var1)) {
                var2.add(var4);
            }
        }

        return var2;
    }

    public int s() {
        return this.r;
    }

    public MinecraftServer c() {
        return this.h;
    }

    public dn t() {
        return null;
    }

    private void a(lm var1, lm var2, agn var3) {
        if (var2 != null) {
            var1.c.a(var2.c.b());
        } else if (this.s != null) {
            var1.c.a(this.s);
        }

        var1.c.b(var3.R().q());
    }

    public void u() {
        for (int var1 = 0; var1 < this.i.size(); ++var1) {
            ((lm) this.i.get(var1)).a.c("Server closed");
        }

    }

    // VanillaIRC start -- redirect for chat injection
    public void a(eu var1, boolean var2) {
        this.sendMessageToPlayers(var1, var2);
        
        String translated = var1.c();
        ChatHandler ch = VanillaIRC.getHandler();
        if (ch != null) {
            ch.handleGameMessage(translated);
        }
    }
    
    public void sendMessageToPlayers(eu var1, boolean var2) {
        this.h.a(var1);
        int var3 = var2 ? 1 : 0;

        this.a((ff) (new fz(var1, (byte) var3)));
    }

    public void sendFromJson(String json) {
        this.sendMessageToPlayers(eu.a.a(json), false);
    } // VanillaIRC end

    public void a(eu var1) {
        this.a(var1, true);
    }

    public nc a(yt var1) {
        UUID var2 = var1.aQ();
        nc var3 = var2 == null ? null : (nc) this.o.get(var2);

        if (var3 == null) {
            File var4 = new File(this.h.a(0).Q().b(), "stats");
            File var5 = new File(var4, var2.toString() + ".json");

            if (!var5.exists()) {
                File var6 = new File(var4, var1.e_() + ".json");

                if (var6.exists() && var6.isFile()) {
                    var6.renameTo(var5);
                }
            }

            var3 = new nc(this.h, var5);
            var3.a();
            this.o.put(var2, var3);
        }

        return var3;
    }

    public void a(int var1) {
        this.r = var1;
        if (this.h.e != null) {
            lk[] var2 = this.h.e;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                lk var5 = var2[var4];

                if (var5 != null) {
                    var5.v().a(var1);
                }
            }

        }
    }

    public List<lm> v() {
        return this.i;
    }

    public lm a(UUID var1) {
        return (lm) this.j.get(var1);
    }

    public boolean f(GameProfile var1) {
        return false;
    }

}
