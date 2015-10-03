/*
 * Copyright (C) 2010-2015 Mojang AB
 *
 * Parts of this program are marked with "VanillaIRC". These will be referred
 * to as "Modification" from this point on.
 *
 * Modification Copyright (C) 2014-2015 MinecraftOnline
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
import com.minecraftonline.vanillairc.ObfuscationHelper; // VanillaIRC
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


public abstract class mg {

    public static final File a = new File("banned-players.json");
    public static final File b = new File("banned-ips.json");
    public static final File c = new File("ops.json");
    public static final File d = new File("whitelist.json");
    private static final Logger f = LogManager.getLogger();
    private static final SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
    private final MinecraftServer h;
    private final List<lm> i = Lists.newArrayList();
    private final Map<UUID, lm> j = Maps.newHashMap();
    private final ml k;
    private final md l;
    private final mh m;
    private final mn n;
    private final Map<UUID, ne> o;
    private ayw p;
    private boolean q;
    protected int e;
    private int r;
    private agz.a s; // VanillaIRC -- faulty decompilation: agz -> agz.a
    private boolean t;
    private int u;

    { ObfuscationHelper.setLoginManager(this, mg.class); } // VanillaIRC -- store static reference to object

    public mg(MinecraftServer var1) {
        this.k = new ml(a);
        this.l = new md(b);
        this.m = new mh(c);
        this.n = new mn(d);
        this.o = Maps.newHashMap();
        this.h = var1;
        this.k.a(false);
        this.l.a(false);
        this.e = 8;
    }

    public void a(ek var1, lm var2) {
        GameProfile var3 = var2.cp();
        mc var4 = this.h.aA();
        GameProfile var5 = var4.a(var3.getId());
        String var6 = var5 == null ? var3.getName() : var5.getName();

        var4.a(var3);
        dn var7 = this.a(var2);

        var2.a((agw) this.h.a(var2.ak));
        var2.c.a((lk) var2.m);
        String var8 = "local";

        if (var1.b() != null) {
            var8 = var1.b().toString();
        }

        f.info(var2.h_() + "[" + var8 + "] logged in with entity id " + var2.H() + " at (" + var2.q + ", " + var2.r + ", " + var2.s + ")");
        lk var9 = this.h.a(var2.ak);
        ayn var10 = var9.T();
        cj var11 = var9.R();

        this.a(var2, (lm) null, var9);
        lv var12 = new lv(this.h, var1, var2);

        var12.a((ff) (new gt(var2.H(), var2.c.b(), var10.s(), var9.s.p().a(), var9.ae(), this.p(), var10.t(), var9.U().b("reducedDebugInfo"))));
        var12.a((ff) (new gh("MC|Brand", (new em(Unpooled.buffer())).a(this.c().getServerModName()))));
        var12.a((ff) (new fw(var10.x(), var10.y())));
        var12.a((ff) (new hu(var11)));
        var12.a((ff) (new gx(var2.bG)));
        var12.a((ff) (new hj(var2.bo.d)));
        this.f(var2);
        var2.C().d();
        var2.C().b(var2);
        this.a((kr) var9.ad(), var2);
        this.h.aC();
        fb var13;

        if (!var2.h_().equalsIgnoreCase(var6)) {
            var13 = new fb("multiplayer.player.joined.renamed", new Object[] { var2.i_(), var6});
        } else {
            var13 = new fb("multiplayer.player.joined", new Object[] { var2.i_()});
        }

        // VanillaIRC start - field "a" blocks class "a"
        try {
            var13.b().a((a) Class.forName("a").getDeclaredField("o").get(null));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        } // VanillaIRC end
        this.a((eu) var13);
        this.c(var2);
        var12.a(var2.q, var2.r, var2.s, var2.w, var2.x);
        this.b(var2, var9);
        if (!this.h.X().isEmpty()) {
            var2.a(this.h.X(), this.h.Y());
        }

        Iterator var14 = var2.bw().iterator();

        while (var14.hasNext()) {
            qw var15 = (qw) var14.next();

            var12.a((ff) (new ic(var2.H(), var15)));
        }

        var2.j_();
        if (var7 != null && var7.b("Riding", 10)) {
            rc var16 = re.a(var7.o("Riding"), (agw) var9);

            if (var16 != null) {
                var16.l = true;
                var9.a(var16);
                var2.a(var16);
                var16.l = false;
            }
        }

    }

    protected void a(kr var1, lm var2) {
        HashSet var3 = Sets.newHashSet();
        Iterator var4 = var1.g().iterator();

        while (var4.hasNext()) {
            azj var5 = (azj) var4.next();

            var2.a.a((ff) (new hs(var5, 0)));
        }

        for (int var9 = 0; var9 < 19; ++var9) {
            azi var10 = var1.a(var9);

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
        this.p = var1[0].S().e();
        var1[0].aj().a(new aqs() {
            public void a(aqu var1, double var2) {
                mg.this.a((ff) (new hh(var1, hh.a.a))); // VanillaIRC -- faulty decompilation: hh -> hh.a
            }

            public void a(aqu var1, double var2, double var4, long var6) {
                mg.this.a((ff) (new hh(var1, hh.a.b))); // VanillaIRC -- faulty decompilation: hh -> hh.a
            }

            public void a(aqu var1, double var2, double var4) {
                mg.this.a((ff) (new hh(var1, hh.a.c))); // VanillaIRC -- faulty decompilation: hh -> hh.a
            }

            public void a(aqu var1, int var2) {
                mg.this.a((ff) (new hh(var1, hh.a.e))); // VanillaIRC -- faulty decompilation: hh -> hh.a
            }

            public void b(aqu var1, int var2) {
                mg.this.a((ff) (new hh(var1, hh.a.f))); // VanillaIRC -- faulty decompilation: hh -> hh.a
            }

            public void b(aqu var1, double var2) {}

            public void c(aqu var1, double var2) {}
        });
    }

    public void a(lm var1, lk var2) {
        lk var3 = var1.w();

        if (var2 != null) {
            var2.w().b(var1);
        }

        var3.w().a(var1);
        var3.r().d((int) var1.q >> 4, (int) var1.s >> 4);
    }

    public int d() {
        return lq.b(this.s());
    }

    public dn a(lm var1) {
        dn var2 = this.h.d[0].T().h();
        dn var3;

        if (var1.h_().equals(this.h.Q()) && var2 != null) {
            var3 = this.h.aI().a((oo) op.b, var2);
            var1.f(var3);
            f.debug("loading single player");
        } else {
            var3 = this.p.b(var1);
        }

        return var3;
    }

    protected void b(lm var1) {
        this.p.a(var1);
        ne var2 = (ne) this.o.get(var1.aS());

        if (var2 != null) {
            var2.b();
        }

    }

    public void c(lm var1) {
        this.i.add(var1);
        this.j.put(var1.aS(), var1);
        this.a((ff) (new gz(gz.a.a, new lm[] { var1}))); // VanillaIRC -- faulty decompilation: gz -> gz.a
        lk var2 = this.h.a(var1.ak);

        for (int var3 = 0; var3 < this.i.size(); ++var3) {
            lm var4 = (lm) this.i.get(var3);

            var1.a.a((ff) (new gz(gz.a.a, new lm[] { var4}))); // VanillaIRC -- faulty decompilation: gz -> gz.a
        }

        var2.a((rc) var1);
        this.a(var1, (lk) null);
    }

    public void d(lm var1) {
        var1.w().w().c(var1);
    }

    public void e(lm var1) {
        var1.b(nj.f);
        this.b(var1);
        lk var2 = var1.w();

        if (var1.k != null) {
            var2.f(var1.k);
            f.debug("removing player mount");
        }

        var2.e(var1);
        var2.w().b(var1);
        this.i.remove(var1);
        UUID var3 = var1.aS();
        lm var4 = (lm) this.j.get(var3);

        if (var4 == var1) {
            this.j.remove(var3);
            this.o.remove(var3);
        }

        this.a((ff) (new gz(gz.a.e, new lm[] { var1}))); // VanillaIRC -- faulty decompilation: gz -> gz.a
    }

    public String a(SocketAddress var1, GameProfile var2) {
        String var4;

        if (this.k.a(var2)) {
            mm var5 = (mm) ((mk) this.k).b(var2); // VanillaIRC -- obfuscation anomaly: cast this.k to mk

            var4 = "You are banned from this server!\nReason: " + var5.d();
            if (var5.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + g.format(var5.c());
            }

            return var4;
        } else if (!this.e(var2)) {
            return "You are not white-listed on this server!";
        } else if (this.l.a(var1)) {
            me var3 = this.l.b(var1);

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
        UUID var2 = zc.a(var1);
        ArrayList var3 = Lists.newArrayList();

        for (int var4 = 0; var4 < this.i.size(); ++var4) {
            lm var5 = (lm) this.i.get(var4);

            if (var5.aS().equals(var2)) {
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

        if (this.h.V()) {
            var9 = new lf(this.h.a(0));
        } else {
            var9 = new ln(this.h.a(0));
        }

        return new lm(this.h, this.h.a(0), var1, (ln) var9);
    }

    public lm a(lm var1, int var2, boolean var3) {
        var1.w().v().b(var1);
        var1.w().v().b((rc) var1);
        var1.w().w().b(var1);
        this.i.remove(var1);
        this.h.a(var1.ak).f(var1);
        cj var4 = var1.ct();
        boolean var5 = var1.cu();

        var1.ak = var2;
        Object var6;

        if (this.h.V()) {
            var6 = new lf(this.h.a(var1.ak));
        } else {
            var6 = new ln(this.h.a(var1.ak));
        }

        lm var7 = new lm(this.h, this.h.a(var1.ak), var1.cp(), (ln) var6);

        var7.a = var1.a;
        var7.a((zc) var1, var3);
        var7.d(var1.H());
        var7.p(var1);
        var7.a(var1.ca());
        Iterator var8 = var1.I().iterator();

        while (var8.hasNext()) {
            String var9 = (String) var8.next();

            var7.a(var9);
        }

        lk var10 = this.h.a(var1.ak);

        this.a(var7, var1, var10);
        cj var11;

        if (var4 != null) {
            var11 = zc.a(this.h.a(var1.ak), var4, var5);
            if (var11 != null) {
                var7.b((double) ((float) var11.p() + 0.5F), (double) ((float) var11.q() + 0.1F), (double) ((float) var11.r() + 0.5F), 0.0F, 0.0F);
                var7.a(var4, var5);
            } else {
                var7.a.a((ff) (new gn(0, 0.0F)));
            }
        }

        var10.r().d((int) var7.q >> 4, (int) var7.s >> 4);

        while (!var10.a((rc) var7, var7.bb()).isEmpty() && var7.r < 256.0D) {
            var7.b(var7.q, var7.r + 1.0D, var7.s);
        }

        var7.a.a((ff) (new hf(var7.ak, var7.m.ae(), var7.m.T().t(), var7.c.b())));
        var11 = var10.R();
        var7.a.a(var7.q, var7.r, var7.s, var7.w, var7.x);
        var7.a.a((ff) (new hu(var11)));
        var7.a.a((ff) (new hp(var7.bJ, var7.bI, var7.bH)));
        this.b(var7, var10);
        this.f(var7);
        var10.w().a(var7);
        var10.a((rc) var7);
        this.i.add(var7);
        this.j.put(var7.aS(), var7);
        var7.j_();
        var7.c(var7.by());
        return var7;
    }

    public void f(lm var1) {
        GameProfile var2 = var1.cp();
        int var3 = this.h(var2) ? this.m.a(var2) : 0;

        var3 = this.h.R() && this.h.d[0].T().u() ? 4 : var3;
        var3 = this.t ? 4 : var3;
        this.a(var1, var3);
    }

    public void a(lm var1, int var2, cj var3) {
        int var4 = var1.ak;
        lk var5 = this.h.a(var1.ak);

        var1.ak = var2;
        lk var6 = this.h.a(var1.ak);

        var1.a.a((ff) (new hf(var1.ak, var1.m.ae(), var1.m.T().t(), var1.c.b())));
        this.f(var1);
        var5.f(var1);
        var1.G = false;
        if (var3 == null) {
            this.a(var1, var4, var5, var6);
        } else if (var1.an()) {
            var5.a((rc) var1, false);
            var1.a(var3, var1.w, var1.x);
            var6.a((rc) var1);
            var6.a((rc) var1, false);
            var1.a((agw) var6);
        }

        this.a(var1, var5);
        var1.a.a(var1.q, var1.r, var1.s, var1.w, var1.x);
        var1.c.a(var6);
        this.b(var1, var6);
        this.g(var1);
        Iterator var7 = var1.bw().iterator();

        while (var7.hasNext()) {
            qw var8 = (qw) var7.next();

            var1.a.a((ff) (new ic(var1.H(), var8)));
        }

    }

    public void a(rc var1, int var2, lk var3, lk var4) {
        double var5 = var1.q;
        double var7 = var1.s;
        double var9 = 8.0D;
        float var11 = var1.w;

        var3.B.a("moving");
        if (var1.ak == -1) {
            var5 = od.a(var5 / var9, var4.aj().b() + 16.0D, var4.aj().d() - 16.0D);
            var7 = od.a(var7 / var9, var4.aj().c() + 16.0D, var4.aj().e() - 16.0D);
            var1.b(var5, var1.r, var7, var1.w, var1.x);
            if (var1.an()) {
                var3.a(var1, false);
            }
        } else if (var1.ak == 0) {
            var5 = od.a(var5 * var9, var4.aj().b() + 16.0D, var4.aj().d() - 16.0D);
            var7 = od.a(var7 * var9, var4.aj().c() + 16.0D, var4.aj().e() - 16.0D);
            var1.b(var5, var1.r, var7, var1.w, var1.x);
            if (var1.an()) {
                var3.a(var1, false);
            }
        } else {
            cj var12;

            if (var2 == 1) {
                var12 = var4.R();
            } else {
                var12 = var4.p();
            }

            var5 = (double) var12.p();
            var1.r = (double) var12.q();
            var7 = (double) var12.r();
            var1.b(var5, var1.r, var7, 90.0F, 0.0F);
            if (var1.an()) {
                var3.a(var1, false);
            }
        }

        var3.B.b();
        if (var2 != 1) {
            var3.B.a("placing");
            var5 = (double) od.a((int) var5, -29999872, 29999872);
            var7 = (double) od.a((int) var7, -29999872, 29999872);
            if (var1.an()) {
                var1.b(var5, var1.r, var7, var1.w, var1.x);
                var4.x().a(var1, var11);
                var4.a(var1);
                var4.a(var1, false);
            }

            var3.B.b();
        }

        var1.a((agw) var4);
    }

    public void e() {
        if (++this.u > 600) {
            this.a((ff) (new gz(gz.a.c, this.i))); // VanillaIRC -- faulty decompilation: gz -> gz.a
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

            if (var4.ak == var2) {
                var4.a.a(var1);
            }
        }

    }

    public void a(zc var1, eu var2) {
        azo var3 = var1.aG();

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

    public void b(zc var1, eu var2) {
        azo var3 = var1.aG();

        if (var3 == null) {
            this.a(var2);
        } else {
            for (int var4 = 0; var4 < this.i.size(); ++var4) {
                lm var5 = (lm) this.i.get(var4);

                if (var5.aG() != var3) {
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

            var2 = var2 + ((lm) var3.get(var4)).h_();
            if (var1) {
                var2 = var2 + " (" + ((lm) var3.get(var4)).aS().toString() + ")";
            }
        }

        return var2;
    }

    public String[] f() {
        String[] var1 = new String[this.i.size()];

        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            var1[var2] = ((lm) this.i.get(var2)).h_();
        }

        return var1;
    }

    public GameProfile[] g() {
        GameProfile[] var1 = new GameProfile[this.i.size()];

        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            var1[var2] = ((lm) this.i.get(var2)).cp();
        }

        return var1;
    }

    public ml h() {
        return this.k;
    }

    public md i() {
        return this.l;
    }

    public void a(GameProfile var1) {
        int var2 = this.h.q();

        ((mk) this.m).a((mj) (new mi(var1, this.h.q(), this.m.b(var1)))); // VanillaIRC -- obfuscation anomaly: cast this.m to mk
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

            var1.a.a((ff) (new gj(var1, var3)));
        }

    }

    public boolean e(GameProfile var1) {
        return !this.q || this.m.d(var1) || this.n.d(var1);
    }

    public boolean h(GameProfile var1) {
        return this.m.d(var1) || this.h.R() && this.h.d[0].T().u() && this.h.Q().equalsIgnoreCase(var1.getName()) || this.t;
    }

    public lm a(String var1) {
        Iterator var2 = this.i.iterator();

        lm var3;

        do {
            if (!var2.hasNext()) {
                return null;
            }

            var3 = (lm) var2.next();
        } while (!var3.h_().equalsIgnoreCase(var1));

        return var3;
    }

    public void a(double var1, double var3, double var5, double var7, int var9, ff var10) {
        this.a((zc) null, var1, var3, var5, var7, var9, var10);
    }

    public void a(zc var1, double var2, double var4, double var6, double var8, int var10, ff var11) {
        for (int var12 = 0; var12 < this.i.size(); ++var12) {
            lm var13 = (lm) this.i.get(var12);

            if (var13 != var1 && var13.ak == var10) {
                double var14 = var2 - var13.q;
                double var16 = var4 - var13.r;
                double var18 = var6 - var13.s;

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
        ((mk) this.n).a((mj) (new mo(var1))); // VanillaIRC -- obfuscation anomaly: cast this.m to mk
    }

    public void c(GameProfile var1) {
        this.n.c(var1);
    }

    public mn k() {
        return this.n;
    }

    public String[] l() {
        return this.n.a();
    }

    public mh m() {
        return this.m;
    }

    public String[] n() {
        return this.m.a();
    }

    public void a() {}

    public void b(lm var1, lk var2) {
        aqu var3 = this.h.d[0].aj();

        var1.a.a((ff) (new hh(var3, hh.a.d))); // VanillaIRC -- faulty decompilation: hh -> hh.a
        var1.a.a((ff) (new hv(var2.P(), var2.Q(), var2.U().b("doDaylightCycle"))));
        if (var2.W()) {
            var1.a.a((ff) (new gn(1, 0.0F)));
            var1.a.a((ff) (new gn(7, var2.j(1.0F))));
            var1.a.a((ff) (new gn(8, var2.h(1.0F))));
        }

    }

    public void g(lm var1) {
        var1.a(var1.bp);
        var1.t();
        var1.a.a((ff) (new hj(var1.bo.d)));
    }

    public int o() {
        return this.i.size();
    }

    public int p() {
        return this.e;
    }

    public String[] q() {
        return this.h.d[0].S().e().f();
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

            if (var4.y().equals(var1)) {
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

    private void a(lm var1, lm var2, agw var3) {
        if (var2 != null) {
            var1.c.a(var2.c.b());
        } else if (this.s != null) {
            var1.c.a(this.s);
        }

        var1.c.b(var3.T().q());
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

        this.a((ff) (new fy(var1, (byte) var3)));
    }

    public void sendFromJson(String json) {
        this.sendMessageToPlayers(eu.a.a(json), false);
    } // VanillaIRC end

    public void a(eu var1) {
        this.a(var1, true);
    }

    public ne a(zc var1) {
        UUID var2 = var1.aS();
        ne var3 = var2 == null ? null : (ne) this.o.get(var2);

        if (var3 == null) {
            File var4 = new File(this.h.a(0).S().b(), "stats");
            File var5 = new File(var4, var2.toString() + ".json");

            if (!var5.exists()) {
                File var6 = new File(var4, var1.h_() + ".json");

                if (var6.exists() && var6.isFile()) {
                    var6.renameTo(var5);
                }
            }

            var3 = new ne(this.h, var5);
            var3.a();
            this.o.put(var2, var3);
        }

        return var3;
    }

    public void a(int var1) {
        this.r = var1;
        if (this.h.d != null) {
            lk[] var2 = this.h.d;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                lk var5 = var2[var4];

                if (var5 != null) {
                    var5.w().a(var1);
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
